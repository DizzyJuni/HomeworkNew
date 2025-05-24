package Lesson_14.jdbc.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final ConnectionConfiguration connectionConfiguration;

    public ConnectionManager(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    connectionConfiguration.getUrl(),
                    connectionConfiguration.getUsername(),
                    connectionConfiguration.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
