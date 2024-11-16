
package seedu.spendswift.parser;

import seedu.spendswift.ui.ErrorMessage;

/**
 * Parses user input strings for components such as names, categories, amounts, and indices.
 * Provides methods for extracting specific parts of an input based on predefined prefixes.
 */
public class InputParser {

    /**
     * Extracts a component from the input string based on the given prefix.
     * Component ends where another prefix starts or at the end of the string.
     *
     * @param input The input string to parse.
     * @param prefix The prefix indicating the start of the component.
     * @return The extracted component as a string, or an empty string.
     */
    private String parseComponent(String input, String prefix) {
        int startIndex = input.indexOf(prefix);
        if (startIndex == -1) {
            return "";
        }

        startIndex += prefix.length();
        int endIndex = input.length();
        String[] prefixes = {
            "n/",
            "a/",
            "c/",
            "e/",
            "l/",
            "hcur/",
            "ocur/"
        };

        for (String otherPrefix: prefixes) {
            if (!otherPrefix.equals(prefix)) {
                int prefixIndex = input.indexOf(otherPrefix, startIndex);
                if (prefixIndex != -1 && prefixIndex < endIndex) {
                    endIndex = prefixIndex;
                }
            }
        }

        return input.substring(startIndex, endIndex).trim();
    }



    /**
     * Parses an indexed component from teh string input using "e/" prefix.
     *
     * @param input The input string to parse.
     * @return The zero-based index as an integer, or -1 if parsing failed.
     */
    public int parseIndex(String input) {
        String indexStr = parseComponent(input, "e/");
        try {
            return Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            return -1;

        }
    }

    public String parseName(String input) {
        return parseComponent(input, "n/");
    }

    public String parseCategory(String input) {
        return parseComponent(input, "c/");
    }
   
    /**
     * Parses a limit component from teh string input using "l/" prefix.
     *
     * @param input The input string with limit to parse.
     * @return The limit as a double.
     */
    public double parseLimit(String input) {
        String limitStr = parseComponent(input, "l/");
        try {
            // Parse the limit string into double
            double limit = Double.parseDouble(limitStr);
            final double quadrillion = 1000000000000000.0;

            // Assertion to ensure the limit does not exceed 1 quadrillion
            assert limit <= quadrillion: "Limit exceeds 1 quadrillion, capping to 1 quadrillion";

            // Cap the limit at 1 quadrillion if it exceeds this value
            if (limit > quadrillion) {
                System.err.println("Limit exceeds 1 quadrillion, capping to 1 quadrillion.");
                limit = quadrillion;
            }

            return limit;
        } catch (NumberFormatException e) {
            System.err.println("Invalid limit format: '" + limitStr + "'.");
            return Double.NaN;
        }
    }



    /**
     * Parses an amount component from the input using "a/" prefix..
     *
     * @param input The input string containing the amount.
     * @return The extracted amount as a double.
     */

    public double parseAmount(String input) {
        String amountStr = parseComponent(input, "a/");
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            ErrorMessage.printInvalidAmount();
            return Double.NaN;
        }
    }
}


  


    
