package currency.app.service;

import java.util.HashMap;
import java.util.Map;

public class CurrencyService {
    public static final
    Map<String, Double> currencies = new HashMap<>();

    static {
        currencies.put("eur", 4.3107);
        currencies.put("pln", 1.);
        currencies.put("usd", 3.9154);
        currencies.put("gbp", 5.0362);
    }

    public static double convert(double amount, String currency1, String currency2) {
        return amount * (currencies.get(currency1) / currencies.get(currency2));
    }

    public static void main(String[] args) {
        double convert1 = convert(100, "pln", "eur");
        double convert = convert(100, "eur", "pln");
        System.out.println(convert1);
        System.out.println(convert);
    }


}
