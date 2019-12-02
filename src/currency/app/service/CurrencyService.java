package currency.app.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import currency.app.currency.ConnectionFactory;
import currency.app.currency.CurrencyDAO;
import currency.app.currency.CurrencyDTO;
import currency.app.currency.CurrencyDTOCurrencyMapper;
import currency.app.model.Currency;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyService {
    public static List<Currency> dailyCurrencyList;
    public static List<Currency> allHistory;

    static {
        //todo load last from database
        dailyCurrencyList = getLastWrittenCurrencies();
        allHistory = getAllHistoryCurrenciesRates();
    }

    private static List<Currency> getAllHistoryCurrenciesRates() {
        List<Currency> allCurrencies = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()){
            CurrencyDAO currencyDAO = new CurrencyDAO(conn);
            allCurrencies = currencyDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCurrencies;
    }

    public static double convert(double amount, String code1, String code2) {
        Currency currency1 = findCurrencyByCode(code1);
        Currency currency2 = findCurrencyByCode(code2);
        return amount * (currency1.getAsk() / currency2.getBid());
    }

    private static Currency findCurrencyByCode(String code) {
        for (Currency currency : dailyCurrencyList) {
            if (currency.getCode().equals(code)){
                return currency;
            }
        }
        throw new RuntimeException("There is no currency with code like this: " + code);
    }

    private static List<Currency> getLastWrittenCurrencies(){
        List<Currency> lastWrittenCurrencies = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()){
            CurrencyDAO currencyDAO = new CurrencyDAO(conn);
            lastWrittenCurrencies = currencyDAO.getLastCurrencies();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastWrittenCurrencies;
    }

    public void saveCurrenciesToDatabase() {
        String response = readJSonFromNBPApi();
        CurrencyDTO[] currencyDTOS;
        try {
            currencyDTOS = mapJSonToCurrencyDTO(response);
        } catch (IOException e) {
            return;
        }

        CurrencyDTOCurrencyMapper mapper = new CurrencyDTOCurrencyMapper();
        for (int i = 0; i < currencyDTOS.length; i++) {
            dailyCurrencyList = mapper.map(currencyDTOS[i]);
            try {
                saveToDatabase(dailyCurrencyList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private CurrencyDTO[] mapJSonToCurrencyDTO(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, CurrencyDTO[].class);
    }

    private String readJSonFromNBPApi() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String url = String.format("http://api.nbp.pl/api/exchangerates/tables/%s/%s/%s/",
                "C", formatter.format(startDate), formatter.format(endDate));

        String responseBody = null;
        try {
            responseBody = Unirest
                    .get(url)
                    .asString()
                    .getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

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
