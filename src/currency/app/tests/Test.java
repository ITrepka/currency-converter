package currency.app.tests;

import currency.app.model.Currency;
import currency.app.service.CurrencyService;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();
        currencyService.saveCurrenciesToDatabase();
        List<Currency> allHistory = CurrencyService.allHistory;
        System.out.println(allHistory);
    }
}
