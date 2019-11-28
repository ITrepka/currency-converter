package currency.app.model;

import java.util.List;
import java.util.Scanner;

public class Screen {
    Scanner scanner;

    {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println(
                "1 - Save month's currency rates to the database\n" +
                "2 - Show all exchange rates for the selected currency\n" +
                "3 - Turn on the currency converter");
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public double readDouble() {return scanner.nextDouble();}

    public <T> void displayCurrencyList(List<T> currencies) {
        for (int i = 0; i < currencies.size(); i++) {
            System.out.println(i + 1 + " - "+ currencies.get(i));
        }
    }

    public void welcomeToConverter() {
        System.out.println("---CONVERTER---");
        System.out.println("Type amount what do you want to exchange: ");
    }

    public void askWhatCurrencyBuying() {
        System.out.println("Please choose curriency what are you want to buy: ");
    }

    public void askWhatCurrencySelling() {
        System.out.println("Please choose curriency which are you interested for sell: ");
    }

    public void displayResult(double result) {
        System.out.println("You get "+ result);
    }

    public int readCurrencyToBuyChoice(List<String> currencies) {
        askWhatCurrencyBuying();
        displayCurrencyList(currencies);
        return readInt();
    }

    public int readCurrencyForSaleChoice(List<String> currencies) {
        askWhatCurrencySelling();
        displayCurrencyList(currencies);
        return readInt();
    }
}
