//@@author glenda-1506
package seedu.spendswift.command;

import seedu.spendswift.ui.ErrorMessage;
import seedu.spendswift.ui.SuccessMessage;
import seedu.spendswift.parser.InputParser;

import java.util.List;

/**
 * Manages methods that are related to the category class in the spendswift application.
 */
public class CategoryManager {

    /**
     * Adds a new category to the tracker data.
     * If the category already exists, an appropriate message is displayed.
     *
     * @param trackerData   The {@code TrackerData} object containing the categories.
     * @param categoryName  The name of the category to be added.
     */
    private static void addCategoryHelper(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();

        String formattedCategoryName = Format.formatInput(categoryName);
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                SuccessMessage.printExistingCategory(formattedCategoryName);
                return;
            }
        }

        Category newCategory = new Category(formattedCategoryName);
        categories.add(newCategory);
        trackerData.setCategories(categories);
        SuccessMessage.printAddCategory(newCategory);
    }

    /**
     * Parses the user input, and adds a category to the tracker data.
     * Displays an error message if the category name is missing.
     *
     * @param input The user input that is going to be parsed, with details.
     * @param trackerData The{@code TrackerData} object containing necessary information.
     */
    public static void addCategory(String input, TrackerData trackerData) {
        InputParser parser = new InputParser();
        String categoryName = parser.parseCategory(input);

        if (categoryName == null || categoryName.isEmpty()) {
            ErrorMessage.printMissingCategory();
            return;
        }

        addCategoryHelper(trackerData, categoryName);
    }


    /**
     * Displays all categories currently stored in tracker data.
     * If no categories exist, appropriate messages will be displayed.
     *
     * @param trackerData The{@code TrackerData} object containing necessary information.
     */
    public void viewAllCategories(TrackerData trackerData) {
        List<Category> categories = trackerData.getCategories();
        if (categories.isEmpty()) {
            SuccessMessage.printNoCategory();
        } else {
            SuccessMessage.printAllCategories(categories);
        }
    }


    /**
     * Helper function to delete a category from the tracker data.
     * If the category is associated with an expense, it cannot be deleted.
     * If the category does not exist, appropriate messages will be displayed.
     *
     * @param trackerData The{@code TrackerData} object containing necessary information.
     * @param categoryName The name of the category to be deleted.
     */
    private static void deleteCategoryHelper(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();
        List<Expense> expenses = trackerData.getExpenses();
        boolean hasTaggedExpenses = false;

        Category categoryToDelete = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                categoryToDelete = category;
                break;
            }
        }

        if (categoryToDelete == null) {
            SuccessMessage.printCategoryDoesNotExist(categoryName);
            return;
        }

        for (Expense expense : expenses) {
            if (expense.getCategory() != null && expense.getCategory().equals(categoryToDelete)) {
                hasTaggedExpenses = true;
                break;
            }
        }

        if (hasTaggedExpenses) {
            SuccessMessage.printCategoryHasExpense(categoryName);
        } else {
            categories.remove(categoryToDelete);
            trackerData.setCategories(categories);
            SuccessMessage.printDeleteCategory(categoryName);
        }
    }

    /**
     * Parses the user input and deletes a category from the tracker data.
     * Displays an error message if the category name is missing or empty.
     *
     * @param input         The user input containing the category name.
     * @param trackerData   The {@code TrackerData} object containing the categories.
     */
    public static void deleteCategory(String input, TrackerData trackerData) {
        InputParser parser = new InputParser();
        String categoryName = parser.parseCategory(input);

        if (categoryName == null || categoryName.isEmpty()) {
            ErrorMessage.printMissingCategory();
            return;
        }

        deleteCategoryHelper(trackerData, categoryName);
    }
}

