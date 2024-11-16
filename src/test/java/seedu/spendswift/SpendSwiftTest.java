//@@author glenda-1506
package seedu.spendswift;
//import java.util.Calendar;

import org.junit.jupiter.api.Test;
import seedu.spendswift.command.Budget;
import seedu.spendswift.command.Category;
import seedu.spendswift.command.CategoryManager;
import seedu.spendswift.command.Expense;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.command.BudgetManager;
import seedu.spendswift.command.Format;
import seedu.spendswift.command.ExpenseManager;






import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import java.util.HashMap;

import java.math.BigDecimal;
//import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ExpenseTest {
    @Test
    public void expenseConstructor() {
        Category category = new Category("Food");
        Expense expense = new Expense("Lunch", 12.50, category);
        assertEquals("Lunch", expense.getName());
        assertEquals(12.50, expense.getAmount());
        assertEquals(category, expense.getCategory());
    }

    @Test
    public void inputWithCategory() {
        Category category = new Category("Entertainment");
        Expense expense = new Expense("Movie", 12.5, category);
        assertEquals(" Item: Movie, Amount: $12.50, Category: Entertainment", expense.toString());
    }

    @Test
    public void inputWithoutCategory() {
        Expense expense = new Expense("Movie", 12.5, null);
        assertEquals(" Item: Movie, Amount: $12.50, Category: null", expense.toString());
    }

    @Test
    public void setCategory() {
        Category category = new Category("Entertainment");
        Expense expense = new Expense("Movie", 12.5, null);
        expense.setCategory(category);
        assertEquals("Entertainment", expense.getCategory().getName());
    }
}

class CategoryTest {
    @Test
    public void categoryConstructor() {
        Category category = new Category("Entertainment");
        assertEquals("Entertainment", category.getName());
    }

    @Test
    public void categoryName() {
        Category category = new Category("Groceries");
        assertEquals("Groceries", category.toString());
    }

    @Test
    public void getName() {
        Category category = new Category("Transport");
        assertNotNull(category.getName());
        assertEquals("Transport", category.getName());
    }
}

class BudgetTest {
    @Test
    public void budgetConstructor() {
        Category category = new Category("Food");
        Budget budget = new Budget(category, 100);
        assertEquals(100, budget.getLimit());
        assertEquals(category, budget.getCategory());
    }

    @Test
    public void setPositiveLimit() {
        Category category = new Category("Transport");
        Budget budget = new Budget(category, 50);
        budget.setLimit(75);
        assertEquals(75, budget.getLimit());
    }

    /*@Test
    public void setNegativeLimit() {
        Category category = new Category("Utilities");
        Budget budget = new Budget(category, 100);
        assertThrows(IllegalArgumentException.class, () -> budget.setLimit(-50));
    }*/

    @Test
    public void budgetOutput() {
        Category category = new Category("Entertainment");
        Budget budget = new Budget(category, 200);
        assertEquals("Budget for category 'Entertainment' is $200", budget.toString());
    }
}

//@@author AdiMangalam
class CategoryManagerTest {
    /*@Test
    void addCategoryNewCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        categoryManager.addCategory("add-category Food", trackerData);

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getCategories().get(0).getName());
    }*/

    @Test
    void addCategoryExistingCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        trackerData.getCategories().add(new Category("Food"));

        categoryManager.addCategory("add-category Food", trackerData);

        assertEquals(1, trackerData.getCategories().size());
    }

    @Test
    void addCategory_doesNotAddIfNameIsEmpty() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        categoryManager.addCategory("add-category  ", trackerData);

        assertTrue(trackerData.getCategories().isEmpty());
    }

    //@@glenda-1506
    /*@Test
    public void add2NonDuplicateCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();
        categoryManager.addCategory("add category Food", trackerData);
        assertEquals(1, trackerData.getCategories().size(), "Should have 1 category");
        categoryManager.addCategory("add category Drinks", trackerData);
        assertEquals(2, trackerData.getCategories().size(), "Should have 2 categories");
    }*/
}

//@@author AdiMangalam
class ExpenseManagerTest {
    /*@Test
    public void addExpenseNewCategory() {
        ExpenseManager expenseManager = new ExpenseManager();
        TrackerData trackerData = new TrackerData();

        expenseManager.addExpense(trackerData, "Coffee", 2.0, "Food");

        assertEquals(1, trackerData.getExpenses().size());
        assertEquals("Coffee", trackerData.getExpenses().get(0).getName());
        assertEquals(2.00, trackerData.getExpenses().get(0).getAmount());
        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getCategories().get(0).getName());
    }

    @Test
    void addExpenseExistingCategory() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        Category existingCategory = new Category("Food");
        trackerData.getCategories().add(existingCategory);

        expenseManager.addExpense(trackerData, "Dinner", 20.00, "Food");

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());
    }

    @Test
    void deleteExpenseValidIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(trackerData, "Coffee", 3.5, "Food");
        expenseManager.addExpense(trackerData, "Bus", 1.2, "Transport");

        assertEquals(2, trackerData.getCategories().size());

        expenseManager.deleteExpense(trackerData, 0);
        assertEquals(1, trackerData.getExpenses().size());
        assertEquals("Bus", trackerData.getExpenses().get(0).getName());
        assertEquals(1.2, trackerData.getExpenses().get(0).getAmount());
        assertEquals("Transport", trackerData.getExpenses().get(0).getCategory().getName());

        expenseManager.deleteExpense(trackerData, 0);
        assertTrue(trackerData.getExpenses().isEmpty());
    }

    @Test
    void deleteExpenseInvalidIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(trackerData, "Chocolates", 2.5, "Snacks");

        expenseManager.deleteExpense(trackerData, 1);

        assertEquals(1, trackerData.getExpenses().size());
        assertEquals("Chocolates", trackerData.getExpenses().get(0).getName());
    }

    @Test
    void tagExpenseValidInput() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(trackerData, "Train", 4, "Transport");

        Category existingCategory = new Category("Travel");
        trackerData.getCategories().add(existingCategory);

        expenseManager.tagExpense(trackerData, "e/1 c/Travel");

        assertEquals("Travel", trackerData.getExpenses().get(0).getCategory().getName());
        assertEquals(2, trackerData.getCategories().size());
    }

    @Test
    void tagExpenseInvalidIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        Category category = new Category("Food");
        trackerData.getCategories().add(category);

        expenseManager.addExpense(trackerData, "Waffle", 11.5, "Food");
        expenseManager.tagExpense(trackerData, "e/2 c/Snack");

        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());
    }

    @Test
    void tagExpenseInvalidCategory() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        Expense expense = new Expense("Bus Fare", 2.50, new Category("Transport"));
        trackerData.getExpenses().add(expense);

        expenseManager.addExpense(trackerData, "Bus", 1.25, "Transport");
        expenseManager.tagExpense(trackerData, "e/1 c/Travel");

        assertEquals("Transport", trackerData.getExpenses().get(0).getCategory().getName());
    }

    //@@author glenda-1506
    @Test
    void tagExpenseInvalidNegativeIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(trackerData, "Lunch", 10.00, "Food");
        expenseManager.tagExpense(trackerData, "e/-1 c/Transport");
        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());
    }

    @Test
    void tagInvalidFormatExpense() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(trackerData, "Train", 4, "Transport");
        expenseManager.tagExpense(trackerData, "e/x c/");
    }

    @Test
    void viewNoExpenses() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.viewExpensesByCategory(trackerData);
    }

    @Test
    void viewMultipleExpensesOneCategory() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(trackerData, "Coffee", 5.0, "Food");
        expenseManager.addExpense(trackerData, "Sandwich", 3.0, "Food");
        expenseManager.viewExpensesByCategory(trackerData);
    }

    @Test
    void viewExpensesMultipleCategory() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(trackerData, "Train", 4.5, "Transport");
        expenseManager.addExpense(trackerData, "Pizza", 12.0, "Food");
        expenseManager.viewExpensesByCategory(trackerData);
    } */

    /*@Test
    void deleteExpenseEmptyList() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.deleteExpense(trackerData, 0);
        assertTrue(trackerData.getExpenses().isEmpty());
    }

    @Test
    void deleteExpenseIndexOutOfBounds() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addExpense(trackerData, "Dinner", 20.00, "Food");
        expenseManager.deleteExpense(trackerData, -1);
        assertEquals(1, trackerData.getExpenses().size());
    }*/
}

class BudgetManagerTest {




    private Category findCategory(TrackerData trackerData, String categoryName) {
        for (Category category: trackerData.getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null; // Return null if no category matches the given name
    }

      
    @Test
   void testViewBudgetsWithNoBudgetsSet() {
        BudgetManager budgetManager = new BudgetManager();
        TrackerData trackerData = new TrackerData();
        String categoryName = "Category_NoBudget";
        ExpenseManager.addExpense(trackerData, "RandomExpense", 100.00, categoryName);
        Category category = findCategory(trackerData, categoryName);
        assertFalse(trackerData.getBudgets().containsKey(category));
    } 

    @Test
    void testAddExpenseExceedingBudget() {
        BudgetManager budgetManager = new BudgetManager();
        TrackerData trackerData = new TrackerData();
        String categoryName = "Travel";
        double budgetLimit = 300.0;
        double expenseAmount = 350.0;
        CategoryManager.addCategory(categoryName,trackerData);
        budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
        ExpenseManager.addExpense(trackerData, "Train Ticket", expenseAmount, categoryName);
        Category category = findCategory(trackerData, categoryName);
        assertNotNull(category);
        assertTrue(expenseAmount > budgetLimit);
        assertEquals(expenseAmount, trackerData.getExpenses().stream()
                .filter(e -> e.getCategory().equals(category))
                .mapToDouble(Expense::getAmount)
                .sum());
    }
    @Test
    void testAddExpenseWithinBudget() {
        BudgetManager budgetManager = new BudgetManager();
        TrackerData trackerData = new TrackerData();
        String categoryName = "Food";
        double budgetLimit = 200.0;
        double expenseAmount = 150.0;
        CategoryManager.addCategory(categoryName, trackerData);
        budgetManager.setBudgetLimit(trackerData, categoryName, budgetLimit);
        ExpenseManager.addExpense(trackerData, "Lunch", expenseAmount, categoryName);
        Category category = findCategory(trackerData, categoryName);
        assertNotNull(category);
        assertEquals(expenseAmount, trackerData.getExpenses().stream()
                .filter(e -> e.getCategory().equals(category))
                .mapToDouble(Expense::getAmount)
                .sum());
    }



    @Test
    void testSetBudgetLimitAtMaximum() {
        BudgetManager budgetManager = new BudgetManager();
        TrackerData trackerData = new TrackerData();
        double maxLimit = 1000000000000000.00; // The maximum budget limit as per your requirements

        // Find the "Education" category
        Category category = findCategory(trackerData, "Education");

        // Manually initialize the Budget for "Education" to avoid NullPointerException
        Budget budget = new Budget(category, maxLimit);
        trackerData.getBudgets().put(category, budget); // Add Budget to the map

        // Now set the budget limit
        budgetManager.setBudgetLimit(trackerData, "Education", maxLimit);

        // Retrieve the set limit
        BigDecimal setLimit = BigDecimal.valueOf(trackerData.getBudgets().get(category).getLimit());

        // Assert that the set limit matches the maximum limit
        assertEquals(0, BigDecimal.valueOf(maxLimit).compareTo(setLimit),
            "The budget limit should be exactly set to the maximum allowed");
    }

    @Test
    void testSetValidBudgetLimit() {

        BudgetManager budgetManager = new BudgetManager();
        TrackerData trackerData = new TrackerData();
        double validLimit = 9999999999999999.99; // The maximum budget limit as per your requirements

        // Find the "Education" category
        Category category = findCategory(trackerData, "Education");

        // Manually initialize the Budget for "Education" to avoid NullPointerException
        Budget budget = new Budget(category, validLimit);
        trackerData.getBudgets().put(category, budget); // Add Budget to the map

        // Now set the budget limit
        budgetManager.setBudgetLimit(trackerData, "Education", validLimit);

        // Retrieve the set limit
        BigDecimal setLimit = BigDecimal.valueOf(trackerData.getBudgets().get(category).getLimit());

        // Assert that the set limit matches the maximum limit
        assertEquals(0, BigDecimal.valueOf(validLimit).compareTo(setLimit),
            "The budget limit is set below  the maximum allowed");

    }


    void testSetInvalidBudgetLimitAboveMaximum() {
        BudgetManager budgetManager = new BudgetManager();
        TrackerData trackerData = new TrackerData();
        final double maxAllowedLimit = 1000000000000000.00; // Define the maximum allowed limit
        final double invalidLimit = maxAllowedLimit + 0.01; // Slightly above the maximum

        // Find the "Education" category
        Category category = findCategory(trackerData, "Education");

        // Ensure that the category is not null


        // Manually initialize the Budget for "Education" with a valid limit to test setting an invalid one
        Budget budget = new Budget(category, maxAllowedLimit);
        trackerData.getBudgets().put(category, budget); // Add Budget to the map

        // Attempt to set the budget limit above the maximum allowed
        budgetManager.setBudgetLimit(trackerData, "Education", invalidLimit);

        // Retrieve the current limit
        BigDecimal currentLimit = BigDecimal.valueOf(trackerData.getBudgets().get(category).getLimit());

        // Assert that the budget limit remains unchanged and is not set above the maximum
        assertEquals(0, BigDecimal.valueOf(maxAllowedLimit).compareTo(currentLimit),
            "The budget limit should remain at the maximum allowed when an invalid limit is attempted");

    }
}



class TrackerDataTest {
    private TrackerData trackerData;
    private Category testCategory;
    private Expense testExpense;
    private Budget testBudget;

    @BeforeEach
    void setUp() {
        trackerData = new TrackerData();
        testCategory = new Category("Food");
        testExpense = new Expense("Lunch", 10.0, testCategory);
        testBudget = new Budget(testCategory, 100.0);

        // Initializing categories, expenses, and budgets for some tests
        trackerData.setCategories(Arrays.asList(testCategory));
        trackerData.setExpenses(Arrays.asList(testExpense));
        HashMap < Category, Budget > budgets = new HashMap < > ();
        budgets.put(testCategory, testBudget);
        trackerData.setBudgets(budgets);
    }

    @Test
    void testGetCategories() {
        assertEquals(Arrays.asList(testCategory), trackerData.getCategories());
    }

    @Test
    void testGetExpenses() {
        assertEquals(Arrays.asList(testExpense), trackerData.getExpenses());
    }

    @Test
    void testGetBudgets() {
        assertEquals(1, trackerData.getBudgets().size());
        assertEquals(testBudget, trackerData.getBudgets().get(testCategory));
    }

    @Test
    void testSetCategories() {
        Category newCategory = new Category("Entertainment");
        trackerData.setCategories(Arrays.asList(newCategory));
        assertEquals(Arrays.asList(newCategory), trackerData.getCategories());
    }

    @Test
    void testSetExpenses() {
        Expense newExpense = new Expense("Movie", 15.0, new Category("Entertainment"));
        trackerData.setExpenses(Arrays.asList(newExpense));
        assertEquals(Arrays.asList(newExpense), trackerData.getExpenses());
    }

    @Test
    void testSetBudgets() {
        Category newCategory = new Category("Travel");
        Budget newBudget = new Budget(newCategory, 300.0);
        HashMap < Category, Budget > newBudgets = new HashMap < > ();
        newBudgets.put(newCategory, newBudget);
        trackerData.setBudgets(newBudgets);
        assertEquals(1, trackerData.getBudgets().size());
        assertEquals(newBudget, trackerData.getBudgets().get(newCategory));
    }
}
class FormatTest {

    @Test
    void testFormatInputNull() {
        assertNull(Format.formatInput(null), "Input is null, should return null.");
    }

    @Test
    void testFormatInputEmptyString() {
        assertEquals("", Format.formatInput(""), "Empty string should return empty string.");
    }

    @Test
    void testFormatInputAllLowerCase() {
        assertEquals("Hello", Format.formatInput("hello"), "Should capitalize first letter and make rest lower case.");
    }

    @Test
    void testFormatInputMixedCase() {
        assertEquals("World", Format.formatInput("wOrLD"), "Should capitalize first letter and make rest lower case.");
    }

    @Test
    void testFormatInputAllUpperCase() {
        assertEquals("Test", Format.formatInput("TEST"), "Should capitalize first letter and make rest lower case.");
    }

    // Tests for formatAmount method

    @Test
    void testFormatAmountRoundToWholeNumber() {
        assertEquals("$123", Format.formatAmount(123.0), "Should format as whole number with dollar sign.");
    }

    @Test
    void testFormatAmountRoundToTwoDecimals() {
        assertEquals("$123.46", Format.formatAmount(123.456), 
                "Should format to two decimal places with dollar sign.");
    }

    @Test
    void testFormatAmountRoundingNecessary() {
        assertEquals("$123.46", Format.formatAmount(123.455), 
                "Should round half up to two decimal places with dollar sign.");
    }

    @Test
    void testFormatAmountExactDecimal() {
        assertEquals("$123.50", Format.formatAmount(123.50), "Should handle exact decimal without rounding.");
    }

    @Test
    void testFormatAmountNegativeValue() {
        assertEquals("-$123.45", Format.formatAmount(-123.45), "Should handle negative values correctly.");
    }
}

