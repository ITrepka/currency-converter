package currency.app.tests;

import currency.app.service.CurrencyService;

public class Test {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();
        currencyService.saveCurrenciesToDatabase();
    }
}
