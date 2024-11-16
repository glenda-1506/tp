package seedu.spendswift.command;

//@@author kq2003
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackerData {
    private List<Category> categories;
    private List<Expense> expenses;
    private Map<Category, Budget> budgets;

    public TrackerData() {
        this.categories = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.budgets = new HashMap<>();
    }

    // Getters
    public List<Category> getCategories() {
        return categories;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Map<Category, Budget> getBudgets() {
        return budgets;
    }

    // Setters
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setBudgets(Map<Category, Budget> budgets) {
        this.budgets = budgets;
    }
}

