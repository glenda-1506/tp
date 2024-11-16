//@@author glenda-1506
package seedu.spendswift.command;

import seedu.spendswift.ui.ErrorMessage;
import seedu.spendswift.ui.SuccessMessage;
import seedu.spendswift.parser.InputParser;

import java.util.List;

/**
 * Manages methods related to expenses in the SpendSwift application.
 * Includes adding, deleting, tagging, and viewing expenses grouped by category.
 */
public class ExpenseManager {


    /**
     * Helper function to add an expense.
     * It parses the input to extract the name, amount, and category of the expense to be added.
     * It validates the parsed values and checks for errors.
     *
     * @param input The user input containing necessary fields.
     * @param trackerData The {@code TrackerData} object containing necessary information.
     */
    //@@author kq2003
    public void addExpenseRequest(String input, TrackerData trackerData) {
        try {
            InputParser parser = new InputParser();
            String name = parser.parseName(input);
            double amount = parser.parseAmount(input);
            String category = parser.parseCategory(input);

            if (name.isEmpty()) {
                ErrorMessage.printExpensesManagerEmptyName();
                return;
            }

            if (category.isEmpty()) {
                ErrorMessage.printMissingCategory();
                return;
            }

            if (Double.isNaN(amount)) {
                return;
            }

            if (amount < 0) {
                ErrorMessage.printExpensesManagerNegativeAmount();
                return;
            }

            addExpense(trackerData, name, amount, category);
        } catch (Exception e) {
            ErrorMessage.printParsingError();
        }
    }

    /**
     * Adds a new expense with the specified name, amount, and category.
     *
     * It checks if the specified category exists in the tracker. If it does not exist,
     * the method creates a new category with the specified name and adds it to the list of categories.
     * Then, it creates a new expense with the given name, amount, and category and adds it to the list of expenses.
     * A message is displayed to confirm the addition of the expense and, if applicable, the new category.
     *
     * @param name         The name of the expense item.
     * @param amount       The amount of the expense.
     * @param categoryName The name of the category to which the expense belongs.
     */
    public static void addExpense(TrackerData trackerData, String name, double amount, String categoryName) {
        List<Expense> expenses = trackerData.getExpenses();
        List<Category> categories = trackerData.getCategories();

        String formattedCategoryName = Format.formatInput(categoryName.trim());
        Category existingCategory = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                existingCategory = category;
                break;
            }
        }
        if (existingCategory == null) {
            existingCategory = new Category(formattedCategoryName);
            categories.add(existingCategory);
            SuccessMessage.printAddExpenseAddCategory(formattedCategoryName);
        }
        Expense newExpense = new Expense(name, amount, existingCategory);
        expenses.add(newExpense);

        // update categories and expenses
        trackerData.setExpenses(expenses);
        trackerData.setCategories(categories);
        SuccessMessage.printAddExpense(newExpense);
    }

    /**
     * Helper function to delete an expense.
     * It parses the input to extract the name, amount, and category of the expense to be deleted.
     * It validates the parsed values and checks for errors.
     *
     * @param input The user input containing necessary fields.
     * @param trackerData The {@code TrackerData} object containing necessary information.
     */
    //@@glenda-1506
    public void deleteExpenseRequest(String input, TrackerData trackerData) {
        try {
            InputParser parser = new InputParser();
            int expenseIndex = parser.parseIndex(input);
            if (expenseIndex < 0) {
                ErrorMessage.printInvalidIndex();
                return;
            }
            deleteExpense(trackerData, expenseIndex);
        } catch (IndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsIndex();
        } catch (Exception e) {
            ErrorMessage.printParsingError();
        }
    }

    //@@author AdiMangalam
    /**
     * Deletes an expense at the specified index in the expense list.
     *
     * This method checks if the provided index is within the valid range of the
     * expense list. If the index is invalid (out of bounds), an error message is displayed.
     * If the index is valid, it removes the expense at the specified index and
     * displays a message confirming the deletion.
     *
     * @param expenseIndex the 0-based index of the expense to delete
     */
    public static void deleteExpense(TrackerData trackerData, int expenseIndex) {
        List<Expense> expenses = trackerData.getExpenses();

        Expense removedExpense = expenses.remove(expenseIndex);
        SuccessMessage.printDeleteExpense(removedExpense);
        trackerData.setExpenses(expenses);
    }

    //@@author MayfairMI6
    /**
     * Displays all expenses grouped by their respective categories.
     *
     * This method first checks if there are any expenses in the tracker. If no
     * expenses are present, it informs the user. If expenses exist, they are grouped
     * by their category and displayed in a formatted manner under each category.
     *
     * Each category is displayed once with its associated expenses listed under it.
     *
     */
    public void viewExpensesByCategory(TrackerData trackerData) {
        List<Expense> expenses = trackerData.getExpenses();
        List<Category> categories = trackerData.getCategories();

        if (expenses.isEmpty() && categories.isEmpty()) {
            SuccessMessage.printNoExpense();
            return;
        }
        SuccessMessage.printExpensesByCategory(expenses, categories);
    }


    /**
     * Handles the user request to tag an expense with a category.
     *
     * It parses the input to extract the index of the designated expense and the category name.
     *
     *
     * @param input The user input containing necessary fields.
     * @param trackerData The {@code TrackerData} object containing necessary information.
     */
    //@@author glenda-1506
    public void tagExpense(TrackerData trackerData, String input) {
        try {
            InputParser parser = new InputParser();
            int expenseIndex = parser.parseIndex(input);

            if (expenseIndex < 0) {
                ErrorMessage.printInvalidIndex();
                return;
            }

            String category = parser.parseCategory(input);

            if (category == null || category.isEmpty()) {
                ErrorMessage.printMissingCategory();
                return;
            }
            tagExpenseHelper(trackerData, expenseIndex, category);
        } catch (IndexOutOfBoundsException e) {
            ErrorMessage.printOutOfBoundsIndex();
        } catch (Exception e) {
            ErrorMessage.printParsingError();
        }
    }

    /**
     * Helper function to tag an expense with specified category.
     * If category does not exist, appropriate messages will be displayed. Otherwise, an expense would be taged with
     * specified category.
     *
     *
     * @param trackerData The {@code TrackerData} object containing necessary information.
     * @param expenseIndex The index of the expense to tag.
     * @param categoryName The name of the category to tag the expense.
     */
    public static void tagExpenseHelper(TrackerData trackerData, int expenseIndex, String categoryName) {
        List<Expense> expenses = trackerData.getExpenses();
        List<Category> categories = trackerData.getCategories();

        String formattedCategoryName = Format.formatInput(categoryName.trim());

        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                Expense expense = expenses.get(expenseIndex);
                expense.setCategory(category);
                SuccessMessage.printTaggedExpense(expense);
                return;
            }
        }

        trackerData.setExpenses(expenses);
        SuccessMessage.printMissingCategory(formattedCategoryName);
    }
}


