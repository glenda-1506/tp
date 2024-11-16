//@@author glenda-1506
package seedu.spendswift.parser;
import seedu.spendswift.command.BudgetManager;
import seedu.spendswift.command.CategoryManager;
import seedu.spendswift.command.ExpenseManager;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.UI;

public class Parser {
    private final ExpenseManager expenseManager;
    private final CategoryManager categoryManager;
    private final BudgetManager budgetManager;
    private final UI ui;


    public Parser(ExpenseManager expenseManager, CategoryManager categoryManager, BudgetManager budgetManager, UI ui) {
        this.expenseManager = expenseManager;
        this.categoryManager = categoryManager;
        this.budgetManager = budgetManager;
        this.ui = ui;
    }

    public boolean parseCommand(String input, TrackerData trackerData) {
        input = input.trim().toLowerCase();

        if (input.startsWith("add-expense")) {
            expenseManager.addExpenseRequest(input, expenseManager, trackerData);
        } else if (input.startsWith("add-category")) {
            CategoryManager.addCategory(trackerData, input);
        } else if (input.startsWith("delete-expense")) {
            expenseManager.deleteExpenseRequest(input, expenseManager, trackerData);
        } else if (input.startsWith("tag-expense")) {
            expenseManager.tagExpense(trackerData, input);
        } else if (input.startsWith("view-budget")) {
            budgetManager.viewBudget(trackerData);
        } else if (input.startsWith("view-category")) {
            CategoryManager.viewAllCategories(trackerData);
        } else if (input.startsWith("set-budget")) {
            budgetManager.setBudgetLimitRequest(input, budgetManager, trackerData);
        } else if (input.startsWith("view-expenses")) {
            expenseManager.viewExpensesByCategory(trackerData);
        } else if (input.startsWith("toggle-reset")) {
            budgetManager.toggleAutoReset();
        } else if (input.startsWith("help")) {
            ui.printHelpMessage();
        } else if (input.startsWith("bye")) {
            ui.printExitMessage();
            return true;
        } else {
            ui.printParserInvalidInput();
        }

        return false;
    }
}
