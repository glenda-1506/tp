//@glenda-1506
package seedu.spendswift;

public class ErrorMessage {
    private static final String UNEXPECTED_ERROR = "Unexpected error: ";
    private static final String INVALID_LIMIT = "Invalid limit format. Please enter a valid number after 'l/'.";
    private static final String INVALID_AMOUNT = "Invalid amount format. Please enter a valid number after 'a/'.";
    private static final String INVALID_INDEX = "Invalid expense index format. " +
            "Please enter a valid positive integer after 'e/'.";
    private static final String EXPENSEMANAGER_EMPTY_NAME = "Invalid input! Please provide a name for your expense.";
    private static final String EXPENSEMANAGER_EMPTY_CATEGORY = "Invalid input! " +
            "Please provide a category.";
    private static final String EXPENSEMANAGER_NEGATIVE_AMOUNT = "Invalid input! Please provide a positive amount!";
    private static final String NEGATIVE_LIMIT = "Invalid input! Please provide a positive limit!";
    private static final String PARSING_ERROR = "Error parsing the input. Please use the correct format.";
    private static final String OUTOFBOUNDS_INDEX = "Invalid input! The specified expense index does not exist. " +
            "Please provide a valid expense index.";
    private static final String MISSING_CATEGORY = "Invalid input! Please provide a category.";

    public static void printInvalidLimit(){
        System.out.println(UI.SEPARATOR);
        System.out.println(INVALID_LIMIT);
        System.out.println(UI.SEPARATOR);
    }

    public static void printInvalidAmount(){
        System.out.println(UI.SEPARATOR);
        System.out.println(INVALID_AMOUNT);
        System.out.println(UI.SEPARATOR);
    }

    public static void printInvalidIndex(){
        System.out.println(UI.SEPARATOR);
        System.out.println(INVALID_INDEX);
        System.out.println(UI.SEPARATOR);
    }

    public static void printOutOfBoundsIndex(){
        System.out.println(UI.SEPARATOR);
        System.out.println(OUTOFBOUNDS_INDEX);
        System.out.println(UI.SEPARATOR);
    }

    public static void printUnexpectedError(Exception e){
        System.out.println(UI.SEPARATOR);
        System.out.println(UNEXPECTED_ERROR + e.getMessage());
        System.out.println(UI.SEPARATOR);
    }

    public static void printExpensesManagerEmptyName(){
        System.out.println(UI.SEPARATOR);
        System.out.println(EXPENSEMANAGER_EMPTY_NAME);
        System.out.println(UI.SEPARATOR);
    }

    public static void printExpensesManagerEmptyCategory(){
        System.out.println(UI.SEPARATOR);
        System.out.println(EXPENSEMANAGER_EMPTY_CATEGORY);
        System.out.println(UI.SEPARATOR);
    }

    public static void printExpensesManagerNegativeAmount(){
        System.out.println(UI.SEPARATOR);
        System.out.println(EXPENSEMANAGER_NEGATIVE_AMOUNT);
        System.out.println(UI.SEPARATOR);
    }

    public static void printNegativeLimit(){
        System.out.println(UI.SEPARATOR);
        System.out.println(NEGATIVE_LIMIT);
        System.out.println(UI.SEPARATOR);
    }

    public static void printParsingError(){
        System.out.println(UI.SEPARATOR);
        System.out.println(PARSING_ERROR);
        System.out.println(UI.SEPARATOR);
    }

    public static void printMissingCategory(){
        System.out.println(UI.SEPARATOR);
        System.out.println(MISSING_CATEGORY);
        System.out.println(UI.SEPARATOR);
    }
}
