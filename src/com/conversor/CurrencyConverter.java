package com.conversor;

import java.util.Scanner;

public class CurrencyConverter {
    private ExchangeRateAPI exchangeRateAPI;
    private Scanner scanner;

    // Códigos de colores ANSI (opcional)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public CurrencyConverter() {
        exchangeRateAPI = new ExchangeRateAPI();
        scanner = new Scanner(System.in);
    }

    public void start() {
        String option;
        do {
            showMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    convertCurrency();
                    break;
                case "2":
                    System.out.println(ANSI_GREEN + "\nGracias por usar el conversor de monedas.");
                    System.out.println("Programa desarrollado por Sergio Carey." + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_RED + "Opción no válida. Intente nuevamente." + ANSI_RESET);
            }
        } while (!option.equals("2"));
    }

    private void showMenu() {
        System.out.println(ANSI_CYAN + "\n==============================");
        System.out.println("       Conversor de Monedas   ");
        System.out.println("==============================");
        System.out.println("          Sergio Carey        ");
        System.out.println("==============================" + ANSI_RESET);
        System.out.println("1. Convertir moneda");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void convertCurrency() {
        System.out.println("\nSeleccione la moneda de origen:");
        ExchangeRateAPI.SUPPORTED_CURRENCIES.forEach((key, value) -> System.out.println(key + ". " + value));
        System.out.print("Ingrese el número correspondiente: ");
        String fromOption = scanner.nextLine();
        String fromCurrency = exchangeRateAPI.getCurrencyCode(fromOption);

        if (fromCurrency == null) {
            System.out.println(ANSI_RED + "Opción no válida para la moneda de origen." + ANSI_RESET);
            return;
        }

        System.out.println("\nSeleccione la moneda de destino:");
        ExchangeRateAPI.SUPPORTED_CURRENCIES.forEach((key, value) -> System.out.println(key + ". " + value));
        System.out.print("Ingrese el número correspondiente: ");
        String toOption = scanner.nextLine();
        String toCurrency = exchangeRateAPI.getCurrencyCode(toOption);

        if (toCurrency == null) {
            System.out.println(ANSI_RED + "Opción no válida para la moneda de destino." + ANSI_RESET);
            return;
        }

        System.out.print("\nIngrese la cantidad a convertir: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0) {
                System.out.println(ANSI_RED + "La cantidad debe ser un número positivo." + ANSI_RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Cantidad inválida. Por favor, ingrese un número válido." + ANSI_RESET);
            return;
        }

        double rate = exchangeRateAPI.getExchangeRate(fromCurrency, toCurrency);
        if (rate != -1) {
            double result = amount * rate;
            System.out.printf(ANSI_GREEN + "\n-------------------------------------\n");
            System.out.printf("Resultado: %.2f %s equivale a %.2f %s\n", amount, fromCurrency, result, toCurrency);
            System.out.printf("-------------------------------------\n" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "No se pudo realizar la conversión. Verifique los códigos de moneda." + ANSI_RESET);
        }
    }
}
