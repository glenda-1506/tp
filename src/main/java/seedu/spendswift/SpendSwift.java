package seedu.spendswift;

import seedu.spendswift.command.BudgetManager;
import seedu.spendswift.command.CategoryManager;
import seedu.spendswift.command.ExpenseManager;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.parser.Parser;
import seedu.spendswift.storage.Storage;
import seedu.spendswift.ui.UI;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point for the SpendSwift application.
 * Manages initialization of data, user interface, and application logic.
 */
public class SpendSwift {

    /**
     * The main method for running the spendswift application.
     */
    public static void main(String[] args) throws IOException {
        String folderPath = "spendswift";
        String expenseFilePath = folderPath + "/expense.txt";
        String categoryFilePath = folderPath + "/category.txt";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        createFileIfNotExists(expenseFilePath);
        createFileIfNotExists(categoryFilePath);

        TrackerData trackerData = new TrackerData();
        UI ui = new UI();
        Storage storage = new Storage(expenseFilePath, categoryFilePath, ui);

        try {
            storage.loadData(trackerData);
        } catch (IOException e) {
            ui.printLoadingError(e.getMessage());
        }

        storage.saveData(trackerData);

        CategoryManager categoryManager = new CategoryManager();
        BudgetManager budgetManager = new BudgetManager();
        ExpenseManager expenseManager = new ExpenseManager();
        Parser parser = new Parser(expenseManager, categoryManager, budgetManager, ui, storage);

        Scanner in = new Scanner(System.in);
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit && in.hasNextLine()) {
            String input = in.nextLine();
            isExit = parser.parseCommand(input, trackerData);
        }
    }

    /**
     * Creates a file if it does not already exist. Prints error if otherwise.
     *
     * @param filePath The path of the created file.
     */
    private static void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + filePath);
                e.printStackTrace();
            }
        }
    }
}
