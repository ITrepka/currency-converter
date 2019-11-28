package currency.app.controller;

import nbp.api.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDTOCurrencyMapper {
    public List<Currency> map(CurrencyDTO currencyDTO){
        List<Currency> dailyCurrencyList = new ArrayList<>();

        for (int i = 0; i < currencyDTO.getRates().size(); i++) {
            dailyCurrencyList.add(new Currency(
                    currencyDTO.getRates().get(i).getCurrency(),
                    currencyDTO.getRates().get(i).getCode(),
                    currencyDTO.getRates().get(i).getBid(),
                    currencyDTO.getRates().get(i).getAsk(),
                    currencyDTO.getTradingDate(),
                    currencyDTO.getEffectiveDate()
            ));
        }
        dailyCurrencyList.add(new Currency("Złoty", "PLN", 1., 1., currencyDTO.getTradingDate(),currencyDTO.getEffectiveDate()));
        return dailyCurrencyList;
    }
}
