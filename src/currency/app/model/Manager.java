package currency.app.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nbp.api.controller.ConnectionFactory;
import nbp.api.controller.CurrencyDAO;
import nbp.api.controller.CurrencyDTO;
import nbp.api.controller.CurrencyDTOCurrencyMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Manager {
    private Screen screen;

    public void runApp() throws UnirestException, IOException, SQLException {
        screen = new Screen();
        screen.displayMenu();
        int choice = screen.readInt();
        handleMenuChoice(choice);
    }

    private void handleMenuChoice(int choice) throws UnirestException, IOException, SQLException {
        switch (choice) {
            case 1:
                saveCurrenciesToDatabase();
                break;
            case 2:
                showAllExchangeRatesForTheSelectedCurrency();
                break;
            case 3:
                converter();
                break;
        }
    }

    private void showAllExchangeRatesForTheSelectedCurrency() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        CurrencyDAO currencyDAO = new CurrencyDAO(conn);
        List<String> currencies = currencyDAO.getCurrenciesList();
        screen.displayCurrencyList(currencies);
        int currencyChoice = screen.readInt();
        List<Currency> currencyHistory = handleCurrencyChoice(currencyChoice, currencies, currencyDAO);
        screen.displayCurrencyList(currencyHistory);
        conn.close();
    }

    private void converter() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        CurrencyDAO currencyDAO = new CurrencyDAO(conn);
        List<String> currencies = currencyDAO.getCurrenciesList();
        screen.welcomeToConverter();
        double amount = screen.readDouble();
        int currencyForSaleChoice = screen.readCurrencyForSaleChoice(currencies);
        int currencyToBuyChoice = screen.readCurrencyToBuyChoice(currencies);
        List<Currency> lastSavedCurrency = currencyDAO.getLastCurrencies();
        double result = calculate(lastSavedCurrency, currencyForSaleChoice, currencyToBuyChoice, amount);
        screen.displayResult(result);
        conn.close();
    }

    private double calculate(List<Currency> lastSavedCurrency, int currencyForSaleChoice, int currencyToBuyChoice, double amount) {
       return lastSavedCurrency.get(currencyForSaleChoice - 1).getAsk() / lastSavedCurrency.get(currencyToBuyChoice - 1).getBid() * amount;
    }

    private List<Currency> handleCurrencyChoice(int currencyChoice, List<String> currencies, CurrencyDAO currencyDAO) throws SQLException {
        return currencyDAO.getSpecifiedCurrencyHistory(currencies.get(currencyChoice - 1));
    }

    private void saveCurrenciesToDatabase() throws UnirestException, IOException, SQLException {
        String response = readJSonFromNBPApi();
        CurrencyDTO[] currencyDTOS = mapJSonToCurrencyDTO(response);

        CurrencyDTOCurrencyMapper mapper = new CurrencyDTOCurrencyMapper();
        for (int i = 0; i < currencyDTOS.length; i++) {
            List<Currency> dailyCurrencyList = mapper.map(currencyDTOS[i]);
            saveToDatabase(dailyCurrencyList);
        }

    }

    private CurrencyDTO[] mapJSonToCurrencyDTO(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, CurrencyDTO[].class);
    }

    private String readJSonFromNBPApi() throws UnirestException {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String url = String.format("http://api.nbp.pl/api/exchangerates/tables/%s/%s/%s/",
                "C", formatter.format(startDate), formatter.format(endDate));

        String responseBody = Unirest
                .get(url)
                .asString()
                .getBody();

        return responseBody;
    }

    private static void saveToDatabase(List<Currency> dailyCurrencyList) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        CurrencyDAO currencyDAO = new CurrencyDAO(conn);
        for (int i = 0; i < dailyCurrencyList.size(); i++) {
            currencyDAO.create(dailyCurrencyList.get(i));
        }
        conn.close();
    }
}
