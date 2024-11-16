package seedu.spendswift.command;

//@@author MayFairMI6
/**
 * Represents a budget assigned to a specific category in the SpendSwift application.
 * A budget includes a spending limit and is associated with a category.
 */
public class Budget {
    private Category category; // Private to prevent unauthorized access or changes
    private double limit; // Private to control modifications to the budget
    private TrackerData trackerData;

    /**
     * Constructs a budget object with specified category and limit.
     *
     * @param category The category object associated with budget.
     * @param limit The spending limit with the budget.
     */
    public Budget(Category category, double limit) {
        this.category = category;
        this.limit = limit;
        this.trackerData= trackerData;
    }


    /**
     * Retrieves the category associated with budget.
     *
     * @return The category associated with budget.
     */
    public Category getCategory() {
        return category;
    }


    /**
     * Retrieves the remaining budget limit associated with the category.
     *
     * @return The spending limit as a double.
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Updates the spending limit.
     *
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }


    /**
     * Returns a string representation of the budget.
     * Includes the associated category name and the formatted spending limit.
     *
     * @return A string describing the budget for a category.
     */
    @Override
    public String toString() {
        return "Budget for category '" + category + "' is " + Format.formatAmount(limit);
    }
}
