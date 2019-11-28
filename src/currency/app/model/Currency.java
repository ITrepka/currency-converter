package currency.app.model;

import java.time.LocalDate;

public class Currency {
    private String currency;
    private String code;
    private Double bid; //przeliczony kurs kupna waluty
    private Double ask; //przeliczony kurs sprzedaży waluty
    private LocalDate tradingDate;
    private LocalDate effectiveDate;

    public Currency(String currency, String code, Double bid, Double ask, LocalDate tradingDate, LocalDate effectiveDate) {
        this.currency = currency;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
        this.tradingDate = tradingDate;
        this.effectiveDate = effectiveDate;
    }


    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", tradingDate=" + tradingDate +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
