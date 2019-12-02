package currency.app.currency;

import currency.app.model.Currency;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {
    private Connection conn;

    public CurrencyDAO(Connection conn) {
        this.conn = conn;
    } //ctrl + q , quick documentation
    public List<Currency> findAll() throws SQLException {
        String sql = "select * from currency";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        String currency, code;
        Double bid, ask;
        LocalDate tradingDate, effectiveDate;
        List<Currency> currencyList = new ArrayList<>();
        while(resultSet.next()){
            currency = resultSet.getString("currency");
            code = resultSet.getString("code");
            bid = resultSet.getDouble("bid");
            ask = resultSet.getDouble("ask");
            tradingDate = LocalDate.parse(resultSet.getString("trading_date"));
            effectiveDate = LocalDate.parse(resultSet.getString("effective_date"));
            currencyList.add(new Currency(currency, code, bid, ask, tradingDate, effectiveDate));
        }
        return currencyList;
    }

    public List<String> getCurrenciesList() throws SQLException {
        String sql = "select distinct currency from currency";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<String> distinctCurrencies = new ArrayList<>();
        while(rs.next()){
            distinctCurrencies.add(rs.getString("currency"));
        }
        return distinctCurrencies;
    }

    public List<Currency> getSpecifiedCurrencyHistory(String s) throws SQLException {
        String sql = "select * from currency where currency = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s);
        ResultSet resultSet = ps.executeQuery();
        String currency, code;
        Double bid, ask;
        LocalDate tradingDate, effectiveDate;
        List<Currency> currencyList = new ArrayList<>();
        while (resultSet.next()){
            currency = resultSet.getString("currency");
            code = resultSet.getString("code");
            bid = resultSet.getDouble("bid");
            ask = resultSet.getDouble("ask");
            tradingDate = LocalDate.parse(resultSet.getString("trading_date"));
            effectiveDate = LocalDate.parse(resultSet.getString("effective_date"));
            currencyList.add(new Currency(currency, code, bid, ask, tradingDate, effectiveDate));
        }
        return currencyList;
    }

    public List<Currency> getLastCurrencies() throws SQLException {
        String sqlCheckLastDate = "select max(effective_date) from currency";
        PreparedStatement ps2 = conn.prepareStatement(sqlCheckLastDate);
        ResultSet rs = ps2.executeQuery();
        rs.next();
        Date date = rs.getDate("max(effective_date)");
        String sql = "select * from currency where effective_date = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, date);
        ResultSet resultSet = ps.executeQuery();
        String currency, code;
        Double bid, ask;
        LocalDate tradingDate, effectiveDate;
        List<Currency> currencyList = new ArrayList<>();
        while (resultSet.next()){
            currency = resultSet.getString("currency");
            code = resultSet.getString("code");
            bid = resultSet.getDouble("bid");
            ask = resultSet.getDouble("ask");
            tradingDate = LocalDate.parse(resultSet.getString("trading_date"));
            effectiveDate = LocalDate.parse(resultSet.getString("effective_date"));
            currencyList.add(new Currency(currency, code, bid, ask, tradingDate, effectiveDate));
        }
        return currencyList;
    }
//    public User findById(int userId) throws SQLException {
//        String sql = "select username, name, surname, email from user where user_id = ?";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1, userId);
//        ResultSet resultSet = ps.executeQuery();
//
//        String username, name, surname, email;
//        while(resultSet.next()){
//            username = resultSet.getString("username");
//            name = resultSet.getString("name");
//            surname = resultSet.getString("surname");
//            email = resultSet.getString("email");
//            return new User(userId,username,name,surname,email);
//        }
//            return null;
//    }


    public Currency create(Currency currency) {
        try {
            String sql = "insert into currency(currency, code, bid, ask, trading_date, effective_date) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, currency.getCurrency());
            ps.setString(2, currency.getCode());
            ps.setDouble(3, currency.getBid());
            ps.setDouble(4, currency.getBid());
            ps.setDate(5, Date.valueOf(currency.getTradingDate()));
            ps.setDate(6, Date.valueOf(currency.getEffectiveDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("duplicate currency");
        }

        return new Currency(currency.getCurrency(), currency.getCode(), currency.getBid(),
                currency.getAsk(), currency.getTradingDate(), currency.getEffectiveDate());
    }


//    public boolean delete(User user) throws SQLException {
//        String sql = "delete from user where user_id = ?";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1, user.getUserId());
//
//        int rowsDeleted = ps.executeUpdate();
//
//        return rowsDeleted > 0;
//    }
//
//    public User update(User user) throws SQLException {
//        String sql = "update user set username = ?, name = ?, surname = ?, email = ?" +
//                "where user_id = ?";
//        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        ps.setString(1, user.getUsername());
//        ps.setString(2, user.getName());
//        ps.setString(3, user.getSurname());
//        ps.setString(4, user.getEmail());
//        ps.setInt(5, user.getUserId());
//
//        return user;
//    }
}
