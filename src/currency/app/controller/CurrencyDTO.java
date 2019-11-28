package currency.app.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {
    private LocalDate tradingDate;
    private LocalDate effectiveDate;
    private List<Rate> rates;

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = LocalDate.parse(tradingDate);
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = LocalDate.parse(effectiveDate);
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "tradingDate=" + tradingDate +
                ", effectiveDate=" + effectiveDate +
                ", rates=" + rates +
                '}';
    }

    public static class Rate {
        private String currency;
        private String code;
        private Double bid;
        private Double ask;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Double getBid() {
            return bid;
        }

        public void setBid(Double bid) {
            this.bid = bid;
        }

        public Double getAsk() {
            return ask;
        }

        public void setAsk(Double ask) {
            this.ask = ask;
        }

        @Override
        public String toString() {
            return "Rate{" +
                    "currency='" + currency + '\'' +
                    ", code='" + code + '\'' +
                    ", bid=" + bid +
                    ", ask=" + ask +
                    '}';
        }
    }
}
