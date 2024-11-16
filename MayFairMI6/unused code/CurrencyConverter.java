package seedu.spendswift;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CurrencyConverter {

    private static final String API_KEY = 
        "04a6ee8bec44c7f011340564e098b97e";  // Load API key from environment or secure storage in a real application
    private static final String BASE_URL = "https://api.exchangeratesapi.io/v1/latest";
    private static Map<String, Double> exchangeRates;
    private static final String BASE_CURRENCY = "EUR"; // The fixed base currency for free plan

    public CurrencyConverter() throws IOException {
        fetchExchangeRates();
    }
    
    // Updated fetchExchangeRates method to use EUR as base currency (fixed for free plan)
    private static void fetchExchangeRates() throws IOException {
        String urlString = BASE_URL + "?access_key=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
            exchangeRates = new Gson().fromJson(jsonObject.get("rates"), 
                                                new TypeToken<Map<String, Double>>(){}.getType());
        } finally {
            connection.disconnect();
        }
    }

    // Convert between any two currencies using EUR as the base reference
    public static double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount; // No conversion needed if currencies are the same
        }
        
        // Retrieve rates from EUR to the specified currencies
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        
        // Convert the amount
        double rate = toRate / fromRate;
        return amount * rate;
    }
}

