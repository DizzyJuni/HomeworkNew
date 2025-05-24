package Lesson_14.jdbc.configuration;

public class ConnectionConfiguration {
    private final String url;
    private final String username;
    private final String password;

    public ConnectionConfiguration(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ConnectionConfiguration() {
        this.url = "jdbc:postgresql://localhost:5432/test_db";
        this.username = "test_user";
        this.password = "test_password";
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
