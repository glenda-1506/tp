//@@author glenda-1506
package seedu.spendswift;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Format {
    public static String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String formatAmount(double amount) {
        BigDecimal roundedAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
        DecimalFormat wholeNumberFormat = new DecimalFormat("$0");
        DecimalFormat decimalFormat = new DecimalFormat("$0.00");
        if (roundedAmount.stripTrailingZeros().scale() <= 0) {
            return wholeNumberFormat.format(roundedAmount);
        } else {
            return decimalFormat.format(roundedAmount);
        }
    }
    //@@author MayFair-MI6
    public static String getFormattedAmount(String inputString) {
        try {
            // Split the input string into its components
            String[] parts = inputString.split(" ");

            // Extract the individual values, assuming a consistent format
            double amount = Double.parseDouble(parts[0]);
            String originalCurrency = parts[1];
            double convertedAmount = Double.parseDouble(parts[2]);
            String homeCurrency = parts[3];

            // Format the output string
            return String.format("%.2f %s (%.2f %s)", amount, originalCurrency, convertedAmount, homeCurrency);

        } catch (Exception e) { // Catch any potential errors (e.g., incorrect input format)
            return "Invalid input format. Please provide a string like '100.00 USD 85.00 EUR'";
        }
    }
}
