//@@author glenda-1506
package seedu.spendswift;

public class UI {
    public static final String SEPARATOR = "_".repeat(50);
    private static final String WELCOME_MESSAGE = "Hiya! How can I assist?";
    private static final String EXIT_MESSAGE = "Goodbye! :> Hope to see you again soon!";
    private static final String FILE_NOT_FOUND = "Data file not found.";
    private static final String DATA_LOADED_MESSAGE = "Data loaded successfully";
    private static final String DATA_SAVED_MESSAGE = "Data has been saved!";
    private static final String ERROR_LOADING_DATA_MESSAGE = "Error loading data: ";
    private static final String ERROR_SAVING_DATA_MESSAGE = "Error saving data: ";
    private static final String INVALID_INPUT_MESSAGE = "Please enter a valid command!";
    private static final String HELP_INTRO_MESSAGE = "Here are the commands available:";
    private static final String HELP_ADD_EXPENSE = "Add expense: add-expense n/NAME a/AMOUNT [c/CATEGORY]";
    private static final String HELP_DELETE_EXPENSE = "Delete expense: delete-expense e/INDEX";
    private static final String HELP_ADD_CATEGORY = "Add new category: add-category CATEGORY";
    private static final String HELP_TAG_EXPENSE = "Tag an expense to a category: tag-expense e/INDEX c/CATEGORY";
    private static final String HELP_VIEW_EXPENSES = "View all recorded expenses: view-expenses";
    private static final String HELP_SET_BUDGET = "Set a budget limit for a category: set-budget c/CATEGORY l/LIMIT";
    private static final String HELP_VIEW_BUDGET = "View current budget status for each category: view-budget";
    private static final String HELP_TOGGLE_RESET = "On/Off automatic budget reset: toggle-reset";
    private static final String HELP_EXIT = "Exit: bye";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printDataLoaded() {
        System.out.println(SEPARATOR);
        System.out.println(DATA_LOADED_MESSAGE);
    }

    public void printDataSaved() {
        System.out.println(SEPARATOR);
        System.out.println(DATA_SAVED_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printLoadingError(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(ERROR_LOADING_DATA_MESSAGE + errorMessage);
    }

    public void printSavingError(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(ERROR_SAVING_DATA_MESSAGE + errorMessage);
        System.out.println(SEPARATOR);
    }

    public void printParserInvalidInput() {
        System.out.println(SEPARATOR);
        System.out.println(INVALID_INPUT_MESSAGE);
        System.out.println(SEPARATOR);
    }

    public void printFileNotFound() {
        System.out.println(SEPARATOR);
        System.out.println(FILE_NOT_FOUND);
    }

    public void printHelpMessage() {
        System.out.println(SEPARATOR);
        System.out.println(HELP_INTRO_MESSAGE);
        System.out.println(HELP_ADD_EXPENSE);
        System.out.println(HELP_DELETE_EXPENSE);
        System.out.println(HELP_ADD_CATEGORY);
        System.out.println(HELP_TAG_EXPENSE);
        System.out.println(HELP_VIEW_EXPENSES);
        System.out.println(HELP_SET_BUDGET);
        System.out.println(HELP_VIEW_BUDGET);
        System.out.println(HELP_TOGGLE_RESET);
        System.out.println(HELP_EXIT);
        System.out.println(SEPARATOR);
    }
}
