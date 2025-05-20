package Lesson_12.task3;

import java.util.Objects;

public class User {

    private final String login;
    private final int passwordHash;


    public User(String login, int passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passwordHash == user.passwordHash && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, passwordHash);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", passwordHash=" + passwordHash +
                '}';
    }
}
