# User Guide

## Introduction

SpendSwift is a simple budgeting tool designed for budget-conscious users.
With intuitive text commands, you can effortlessly track expenses and manage your finances
without the complexity of traditional tools.

## Quick Start

1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest `[CS2113-T10-4][SpendSwift].jar` file.
3. Copy the file to the folder you want to use as the home folder for your Task Manager.
4. Open a command terminal, cd into the folder where you placed the jar file,
   and run the following command to start the application: `java -jar "[CS2113-T10-4][SpendSwift].jar".`
5. The application will start, and any existing expenses, categories and budgets will be loaded from the folder
   spendswift with 2 text files in it, expense.txt and category.txt.
   - If this file doesn't exist, it will be created automatically when expenses, categories and budgets are saved
     after exiting the program properly.
6. Type commands below the outputs and press Enter to execute them.

Your command terminal should look similar to the one below.

![welcome](userguidepictures/welcome.png)

If the line separator have overlaps, please extend your terminal screen or reduce your font size. For example:

![separator](userguidepictures/separator.png)

For details on all available commands, refer to the Features section below.

---

<div style="page-break-after: always;"></div>

## Features
### Command Format Notes
1. Commands are case-insensitive: Command words can be typed in any combination of upper or lower case.

   Example: `add-expense` and `ADD-EXPENSE` are both valid.
2. Words in UPPER_CASE are parameters to be supplied by the user.

   Example: In `add n/NAME`, `NAME` is a parameter, which can be used as `add n/Udon`.

3. All parameters must be provided for commands that require them.

   Example: In `add-expense n/NAME a/AMOUNT c/CATEGORY`, you must provide the expense name, amount and category,
   such as `add-expense n/Coffee a/5.50 c/Drinks`

4. Order of parameters: Parameters can be provided in any order. However, please note that a valid command must be input first. 

   Example: `add-expense n/Coffee a/5.50 c/Food` is equivalent to `add-expense a/5.50 n/Coffee c/Food`. However, `n/Coffee add-expense a/5.50 c/Food` would produce an error.

5. Extraneous parameters that appear after commands which do not take in parameters (such as `view-expenses` and `bye`) 
   will be ignored.

   Example: If the command specifies `view-expenses 123`, it will be interpreted as `view-expenses`. However, `123 view-expense` would produce an error.

6. Avoid using command prefixes (e.g., `n/`, `a/`, `c/`, etc.) and special character `|` within parameter values, as they may interfere with parsing.

   Example: `add-expense n/Coffee 2023/02/12 a/5.50 c/Food` is valid, but `n/Coffee a/s/a/a/a/ a/5.50 c/Food` is not,
   as `a/` is used within the `NAME` parameter, causing confusion with the `a/AMOUNT` prefix.

<div style="page-break-after: always;"></div>

### Add an Expense: add-expense
This command allows you to record a new expense.

Format:
`add-expense n/NAME a/AMOUNT c/CATEGORY`

- `NAME`

  - The name of the expense.
  - This parameter is required.

- `AMOUNT`

  - The monetary value of the expense.
  - This input must be an integer or a double.
  - This parameter is required.
  - When the input is an integer, the input would be saved as an integer.
  - When the input is a double, the input would be saved to 2 decimal places.
    If the input has more than 2 decimal places, it would be rounded off to the nearest 2 decimal places.

- `CATEGORY`

  - The category under which the expense will be recorded.
  - The category is case-insensitive. It can be typed in any combination of upper or lower case.
  - If the category has not been created, this command will automatically create the category.

Example:

![add-expense](userguidepictures/add-expense.png)

<div style="page-break-after: always;"></div>

### Delete an Expense: delete-expense
This command removes an existing expense from the record.

Format:
`delete-expense e/INDEX`

- `INDEX`
  - The index number of the expense, as shown in the expense list.
  - The index refers to the index number shown in the displayed person list.
  - The index must be a positive integer 1, 2, 3, ...
  - This parameter is required.

Example:

![delete-expense](userguidepictures/delete-expense.png)

### Add a Category: add-category
Create a new category under which expenses can be categorised.

Format:
`add-category c/CATEGORY`

- `CATEGORY`
  - The name of the new category.
  - This parameter is required.
  - The category is case-insensitive. It can be typed in any combination of upper or lower case.

Example:

![add-category](userguidepictures/add-category.png)

<div style="page-break-after: always;"></div>

### Delete a Category: delete-category
Delete an existing category, with no expenses tagged to it.

Format:
`delete-category c/CATEGORY`

- `CATEGORY`
    - The name of the existing category.
    - This parameter is required.
    - The category is case-insensitive. It can be typed in any combination of upper or lower case.
    - The category must not have any expenses tagged to it when it is deleted.

Example:

![delete-category](userguidepictures/delete-category.png)

### Tag an Expense to a Category: tag-expense
Assign or change the category of an existing expense.

Format:
`tag-expense e/INDEX c/CATEGORY`

- `INDEX`
  - The index number of the expense, as shown in the expense list.
  - The index refers to the index number shown in the displayed person list.
  - The index must be a positive integer 1, 2, 3, ...
  - This parameter is required.

- `CATEGORY`
  - The name of the category to assign the expense to.
  - The category is case-insensitive. It can be typed in any combination of upper or lower case.
  - This must be an existing category. This must be an existing category. If the category has not been created,
    use the `add-category` command to create the category first, before using this command to tag an expense. 
  - This parameter is required.

Example:

![tag-expense](userguidepictures/tag-expense.png)

<div style="page-break-after: always;"></div>

### Add Budget Limit to a Category: set-budget
Set a maximum spending limit for a category for the current month.

Format:
`set-budget c/CATEGORY l/LIMIT`

- `CATEGORY`
  - The name of the category to set the budget for.
  - The category is case-insensitive. It can be typed in any combination of upper or lower case.
  - This must be an existing category. This must be an existing category. If the category has not been created,
    use the `add-category` command to create the category first, before using this command to set budget limit.
  - This parameter is required.

- `LIMIT`
  - The maximum spending limit for this category in the current month.
  - This input must be an integer or a double.
  - This parameter is required.
  - When the input is an integer, the input would be saved as an integer.
  - When the input is a double, the input would be saved to 2 decimal places.
    If the input has more than 2 decimal places, it would be rounded off to the nearest 2 decimal places.
  - The application does not support limits above 1 quadrillion. Any limit entered above this amount would be rounded down to 1             quadrillion(1,000,000,000,000,000).

Example:

![set-budget](userguidepictures/set-budget.png)

<div style="page-break-after: always;"></div>

### View All Expenses: view-expenses
Displays all expenses recorded, grouped by its categories.

Format:
`view-expenses`

- No parameters are required.
- Displays a list of all recorded expenses, showing the name, amount, category, and its index.

Example output:

![view-expenses](userguidepictures/view-expenses.png)

### View All Categories: view-category
Displays all categories, without expenses and their budget limit.

Format:
`view-category`

- No parameters are required.
- Displays a list of all recorded categories.

Example output:

![view-category](userguidepictures/view-category.png)

### View Budget for Each Category: view-budget
View the total expenses for each category and how much remains before the budget limit is reached.

Format:
`view-budget`

- No parameters are required.
- Displays a summary of spending and remaining budget for all categories with a budget limit.

Example output:

![view-budget](userguidepictures/view-budget.png)

### Help
Provides a summary of available commands and their functionalities.
It serves as a quick reference for users to understand how to use different features and commands in the system.

Format:
`help`

Example output:

![help](userguidepictures/help.png)

### Exiting the program: bye
Exits the program, and saves the data for that session.
If the data file has yet to be created, this command would also create the data folder with the 2 data text files in it.

Format:

![bye](userguidepictures/bye.png)

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer?
**A**: Copy the `spendswift` folder from your SpendSwift home folder to the same folder on your new computer.
Ensure the `[CS2113-T10-4][SpendSwift].jar` file is also in the same folder. When you start the application on the new computer,
it will load your saved expenses and categories from `spendswift`.

**Q**: What happens if I enter an invalid command or make a typo?  
**A**: SpendSwift will display an error message if it doesn't recognize the command.
Double-check the command format and ensure all required parameters are included.
You can type `help` to see the correct command syntax and available options.

**Q**: Can I update or change an expense’s details after adding it?  
**A**: Currently, SpendSwift doesn't support direct editing of expenses.
To make changes, delete the existing expense using `delete-expense` and add a new one with the updated details.

**Q**: How are expenses rounded off if I enter an amount with more than two decimal places?  
**A**: SpendSwift automatically rounds amounts to two decimal places.
For example, if you enter an amount of 5.678, it will be saved as 5.68.

## Command Summary
- Add expense `add-expense n/NAME a/AMOUNT c/CATEGORY`
- Delete expense `delete-expense e/INDEX`
- Add category `add-category c/CATEGORY`
- Delete category `delete-category c/CATEGORY`
- Tag expense to category `tag-expense e/INDEX c/CATEGORY`
- View expenses `view-expenses`
- View categories `view-category`
- Set budget `set-budget c/CATEGORY l/LIMIT`
- View budget `view-budget`
- Exit `bye`
