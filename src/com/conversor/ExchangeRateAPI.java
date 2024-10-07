package com.conversor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.LinkedHashMap;

public class ExchangeRateAPI {
    private static final String API_KEY = "9be4274c639c20a33a6b5fc2"; // Reemplaza con tu clave de API
    public static final LinkedHashMap<String, String> SUPPORTED_CURRENCIES = new LinkedHashMap<>() {{
        put("1", "ARS - Peso argentino");
        put("2", "BOB - Boliviano boliviano");
        put("3", "BRL - Real brasileño");
        put("4", "CLP - Peso chileno");
        put("5", "COP - Peso colombiano");
        put("6", "USD - Dólar estadounidense");
    }};

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, fromCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            if (!jsonObject.get("result").getAsString().equals("success")) {
                System.out.println("Error en la respuesta de la API.");
                return -1;
            }

            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            double rate = conversionRates.get(toCurrency).getAsDouble();

            return rate;

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener la tasa de cambio: " + e.getMessage());
            return -1;
        }
    }

    public String getCurrencyCode(String option) {
        switch (option) {
            case "1":
                return "ARS";
            case "2":
                return "BOB";
            case "3":
                return "BRL";
            case "4":
                return "CLP";
            case "5":
                return "COP";
            case "6":
                return "USD";
            default:
                return null;
        }
    }
}
