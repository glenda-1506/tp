package seedu.spendswift.command;

import seedu.spendswift.Format;
import seedu.spendswift.parser.InputParser;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@@author kq2003
public class BudgetManager {
    public boolean isAutoResetEnabled;
    public int lastResetMonth;
    private String homeCurrency;
    


    public BudgetManager() {
        this.lastResetMonth = -1;
        this.isAutoResetEnabled = false;
        this.homeCurrency = homeCurrency;
    }

    public boolean getAutoResetStatus() {
        return this.isAutoResetEnabled;
    }

    

    public void toggleAutoReset() {
        isAutoResetEnabled = !isAutoResetEnabled;
        System.out.println("Automatic budget reset is now " + (isAutoResetEnabled ? "ON" : "OFF") + ".");
    }

    //@@author AdiMangalam
    /**
     * Checks if it is a new month and resets budgets if auto-reset is enabled.
     *
     * This method uses the current month to determine if a monthly budget reset
     * should occur. If auto-reset is enabled and the month has changed since the
     * last reset, it triggers a reset of all budgets and updates the last reset month.
     */
    public void checkAndResetBudgets(TrackerData trackerData) {

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);

        if (isAutoResetEnabled && currentMonth != lastResetMonth) {
            resetBudgets(trackerData);
            lastResetMonth = currentMonth;
        }
    }

    /**
     * Manages the monthly budget reset process.
     *
     * This method is intended to be called periodically to ensure budgets are reset
     * at the start of a new month if necessary. It delegates the actual reset logic
     * to the checkAndResetBudgets method, which handles auto-reset checks.
     */
    public void manageMonthlyReset(TrackerData trackerData) {
        checkAndResetBudgets(trackerData);
    }

    /**
     * Resets the budget limits for all categories.
     *
     * This method iterates over all budgets in the tracker and resets each budget's
     * limit as per the current configuration. By default, it maintains the same limit
     * for each budget, but the reset logic can be adjusted if needed.
     */
    public void resetBudgets(TrackerData trackerData) {
        Map < Category, Budget > budgets = trackerData.getBudgets();

        for (Budget budget: budgets.values()) {
            // Resetting the budget logic can be adjusted as needed
            budget.setLimit(budget.getLimit()); // For now, just maintaining the same limit
        }

        trackerData.setBudgets(budgets);
        System.out.println("Budgets have been reset for all categories.");
    }

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
    public static void setBudgetLimit(TrackerData trackerData, String categoryName, double limit, String homeCurrency) {

        List < Category > categories = trackerData.getCategories();
        Map < Category, Budget > budgets = trackerData.getBudgets();
        String formattedCategoryName = Format.formatInput(categoryName.trim());

        if (limit < 0) {
            System.out.println("Invalid input! Please provide a positive amount!");
            return;
        }

        Category existingCategory = null;
        for (Category category: categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                existingCategory = category;
                break;
            }
        }

        if (existingCategory == null) {
            System.out.println("Category '" + formattedCategoryName + "' not found. Please add the category first.");
            return;
        }

        if (budgets.containsKey(existingCategory)) {
            budgets.get(existingCategory).setLimit(limit);
            System.out.println("Updated budget for category '" + existingCategory + "' to " +
                Format.formatAmount(limit) + " " + homeCurrency); // Added homeCurrency to output
        } else {
            Budget newBudget = new Budget(existingCategory, limit,
                                          homeCurrency); // Pass homeCurrency to constructor
            budgets.put(existingCategory, newBudget);
            System.out.println("Set budget for category '" + existingCategory + "' to " +
                Format.formatAmount(limit) + " " + homeCurrency); // Added homeCurrency to output
        }

        trackerData.setBudgets(budgets); // This line remains the same
    }
    //@author MayFairMI6
    public int getLastResetMonth() {
        return lastResetMonth;
    }


    //@@author kq2003
    public void setBudgetLimitRequest(String input, BudgetManager budgetManager, TrackerData trackerData) {
        try {
            InputParser parser = new InputParser();
            String category = parser.parseCategory(input);
            double limit = parser.parseLimit(input);
            String homeCurrency = parser.parseHomeCurrency(input);

            if (category == null || category.isEmpty() || limit == 0) {
                System.out.println("Invalid input! Please provide category name and limit.");
                return;
            }

            budgetManager.setBudgetLimit(trackerData, category, limit, homeCurrency);
        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use the correct format for set-budget commands.");
        }
    }

    //@@author MayFairMI6
    public void simulateMonthChange() {
        Calendar calendar = Calendar.getInstance();
        lastResetMonth = (calendar.get(Calendar.MONTH) + 1) % 12; // Simulate advancing by one month
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

        List < Expense > expenses = trackerData.getExpenses();
        Map < Category, Budget > budgets = trackerData.getBudgets();

        if (budgets.isEmpty()) {
            System.out.println("No budgets set for any category.");
            return;
        }

        // mapping total expenses for a category to each category
        Map < Category, Double > totalExpensesToCategory = new HashMap < > ();
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
                System.out.println(category + ": " + 
                                   Format.formatAmount(totalExpense) + " " + homeCurrency + " spent, " +
                    Format.formatAmount(remainingBudget) + " " + homeCurrency + " remaining");
            } else {
                Double positive = Math.abs(remainingBudget);
                System.out.println(category + ": " + Format.formatAmount(totalExpense) + 
                                   " " + homeCurrency + " spent, " +
                    "Over budget by " + Format.formatAmount(positive) + " " + homeCurrency); // Added homeCurrency code
            }
        }

        // if no budget set for certain category
        for (Category category: totalExpensesToCategory.keySet()) {
            if (!budgets.containsKey(category)) {
                System.out.println(category + ": No budget set");
            }
        }
    }
}
