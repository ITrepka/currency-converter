package currency.app.currency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return
                DriverManager
                        .getConnection("jdbc:mysql://127.0.0.1:3306/currency_rates_history?serverTimezone=UTC", "root", "root");
    }
}
