//@@author glenda-1506
package seedu.spendswift.command;

import seedu.spendswift.Format;

import java.util.List;

public class CategoryManager {
    

    public static void addCategory(TrackerData trackerData, String categoryName) {
        //@@author MayFairMI6
        if (categoryName == null || categoryName.length() <= "add category".length()) {
            System.out.println("Invalid category name input!");
            return;
        }
        //@@author glenda-1506
        List<Category> categories = trackerData.getCategories();
        String trimmedCategoryName = categoryName.substring("add category".length()).trim();
        if (trimmedCategoryName.isEmpty()) {
            System.out.println("Category name is empty!");
            return;
        }

        String formattedCategoryName = Format.formatInput(trimmedCategoryName.trim());
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                System.out.println("Category '" + formattedCategoryName + "' already exists!");
                return;
            }
        }
        Category newCategory = new Category(formattedCategoryName);
        categories.add(newCategory);
        trackerData.setCategories(categories);
        System.out.println("Category '" + newCategory + "' added successfully.");
    }

    public static void viewAllCategories(TrackerData trackerData) {
        List<Category> categories = trackerData.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
        } else {
            System.out.println("Categories:");
            int index = 1;
            for (Category category : categories) {
                System.out.println(index + ". " + category.getName());
                index++;
            }
        }
    }
}

