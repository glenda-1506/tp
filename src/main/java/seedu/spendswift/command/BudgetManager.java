package seedu.spendswift.command;

import seedu.spendswift.ui.ErrorMessage;
import seedu.spendswift.ui.SuccessMessage;
//import seedu.spendswift.ui.UI;
import seedu.spendswift.parser.InputParser;

//import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;


//@@author kq2003
/**
 * Manages methods that are related to the budget class in the spendswift application.
 */
public class BudgetManager {

    //@@author MayFairMI6
    /**
     * Sets a budget limit for a specific category.
     *
     * If the category already has a budget, this method updates the budget limit.
     * If the category does not have a budget set, it creates a new budget for the category.
     *
     * This method is used to track and control spending limits for different categories.
     * After setting the budget, a message is displayed to confirm the action.
     *
     * @param categoryName The name of the category to set the budget for
     * @param limit The budget limit to be set for the category (in dollars)
     */
    public static void setBudgetLimit(TrackerData trackerData, String categoryName, double limit) {
        // Adjusted for potentially enormous values typical in some currencies
        final BigDecimal maxLimit = new BigDecimal("1000000000000000"); // 1 quadrillion for example

        BigDecimal preciseLimit = BigDecimal.valueOf(limit);
        List<Category> categories = trackerData.getCategories();
        Map<Category, Budget> budgets = trackerData.getBudgets();
        String formattedCategoryName = Format.formatInput(categoryName.trim());

        if (preciseLimit.compareTo(maxLimit) > 0) {
            ErrorMessage.printExceedMax(maxLimit);
            return;
        }
        Category existingCategory = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                existingCategory = category;
                break;
            }
        }

        if (existingCategory == null) {
            SuccessMessage.printMissingCategory(formattedCategoryName);
            return;
        }

        if (budgets.containsKey(existingCategory)) {
            budgets.get(existingCategory).setLimit(preciseLimit.doubleValue());
            SuccessMessage.printExistingLimit(existingCategory, preciseLimit);
            budgets.get(existingCategory).setLimit(limit);
        } else {
            Budget newBudget = new Budget(existingCategory, preciseLimit.doubleValue());
            budgets.put(existingCategory, newBudget);
            SuccessMessage.printNewLimit(existingCategory, preciseLimit);
        }

        trackerData.setBudgets(budgets);
    }


    //@@author kq2003
    /**
     * Helper function to handle the user request to set a budget limit for a category.
     * Parses the input to extract category name and limit, validates the values,
     * and delegates the operation to setBudgetLimit.
     *
     * @param input         The user input containing the category and budget limit.
     * @param budgetManager The {@code BudgetManager} instance to handle the request.
     * @param trackerData   The {@code TrackerData} containing the current categories and budgets.
     */
    public void setBudgetLimitRequest(String input, BudgetManager budgetManager, TrackerData trackerData) {
        try {
            InputParser parser = new InputParser();
            String category = parser.parseCategory(input);
            double limit = parser.parseLimit(input);

            if (category.isEmpty()) {
                ErrorMessage.printMissingCategory();
                return;
            }

            if (Double.isNaN(limit)) {
                return;
            }

            if (limit < 0) {
                ErrorMessage.printNegativeLimit();
                return;
            }

            setBudgetLimit(trackerData, category, limit);
        } catch (Exception e) {
            ErrorMessage.printParsingError();
        }
    }


    /**
     * Displays the current budget status for each category.
     *
     * This method checks if each category has a budget set, calculates the total expenses for that category,
     * and shows the remaining budget. If the total expenses exceed the budget limit, it displays the amount
     * over budget. Categories with expenses but no budget set are also displayed.
     * If no budgets are set, a message is shown indicating the absence of budgets.
     */
    public void viewBudget(TrackerData trackerData) {
        List<Expense> expenses = trackerData.getExpenses();
        Map<Category, Budget> budgets = trackerData.getBudgets();

        if (budgets.isEmpty()) {
            SuccessMessage.printNoBudgetForAll();
            return;
        }

        // mapping total expenses for a category to each category
        Map<Category, Double> totalExpensesToCategory = new HashMap<>();
        for (Expense expense: expenses) {
            Category category = expense.getCategory();
            if (totalExpensesToCategory.containsKey(category)) {
                totalExpensesToCategory.put(category, totalExpensesToCategory.get(category) + expense.getAmount());
            } else {
                totalExpensesToCategory.put(category, expense.getAmount());
            }
        }

        // Calculate remaining budget, and display as needed
        for (Category category: budgets.keySet()) {
            Budget budget = budgets.get(category);
            double totalExpense = totalExpensesToCategory.getOrDefault(category, 0.0);
            double remainingBudget = budget.getLimit() - totalExpense;

            if (remainingBudget >= 0) {
                SuccessMessage.printWithinBudget(category, totalExpense, remainingBudget);
            } else {
                Double positive = Math.abs(remainingBudget);
                SuccessMessage.printOverBudget(category, totalExpense, positive);
            }
        }

        // if no budget set for certain category
        for (Category category: totalExpensesToCategory.keySet()) {
            if (!budgets.containsKey(category)) {
                SuccessMessage.printNoBudget(category);
            }
        }
    }
}
