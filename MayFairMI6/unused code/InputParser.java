package seedu.spendswift.parser;
import seedu.spendswift.ErrorMessage;

public class InputParser {
    private String parseComponent(String input, String prefix) {
        int startIndex = input.indexOf(prefix);
        if (startIndex == -1) {
            return "";
        }

        startIndex += prefix.length();
        int endIndex = input.length();
        String[] prefixes = {"n/", "a/", "c/", "e/", "l/", "hcur/", "ocur/"};

        for (String otherPrefix : prefixes) {
            if (!otherPrefix.equals(prefix)) {
                int prefixIndex = input.indexOf(otherPrefix, startIndex);
                if (prefixIndex != -1 && prefixIndex < endIndex) {
                    endIndex = prefixIndex;
                }
            }
        }

        return input.substring(startIndex, endIndex).trim();
    }

    public String parseHomeCurrency(String input) throws IllegalArgumentException {
        return parseCurrency(input, "hcur/");
    }

    public String parseOriginalCurrency(String input) throws IllegalArgumentException {
        return parseCurrency(input, "ocur/");
    }

    private String parseCurrency(String input, String currencyPrefix) throws IllegalArgumentException {
        // Ensure the currency code follows immediately after the prefix and is exactly three letters long
        if (!input.matches(".*" + currencyPrefix + "[a-zA-Z]{3}(\\b|\\s|$).*")) {
            throw new IllegalArgumentException("Error: A 3-letter currency code directly following '" 
                                               + currencyPrefix + "' is needed.");
        }

        String currency = parseComponent(input, currencyPrefix);
        if (currency.isEmpty() || !currency.matches("[a-zA-Z]{3}")) {
            throw new IllegalArgumentException("Error: A valid 3-letter currency code is needed after '" 
                                               + currencyPrefix + "'. Found: '" + currency + "'");
        }

        return currency.toUpperCase();
    }

    public String parseName(String input) {
        return parseComponent(input, "n/");
    }

    public String parseCategory(String input) {
        return parseComponent(input, "c/");
    }
    public double parseLimit(String input) {
        String limitStr = parseComponent(input, "l/");
        try {
            return Double.parseDouble(limitStr);
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidLimit();
            return Double.NaN;
        }
    }

    public double parseAmount(String input) {
        String amountStr = parseComponent(input, "a/");
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidAmount();
            return Double.NaN;
        }
    }
   
    public int parseIndex(String input) {
        String indexStr = parseComponent(input, "e/");
        try {
            return Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}




