package Lesson_12.task3;

import Lesson_8.Exception.AuthException;
import Lesson_8.Exception.UserLimitExceededException;
import Lesson_8.Exception.WrongLoginException;
import Lesson_8.Exception.WrongPasswordException;

import java.util.Arrays;
import java.util.Objects;

public class Service implements LoginService {
    private final int limit;
    private User user;
    private final User[] database;
    private int counter;

    public Service(int limit) {
        this.limit = limit;
        this.database = new User[limit];
    }

    @Override
    public void register(final String login, final String password, final String confirmPassword) throws WrongLoginException, WrongPasswordException, UserLimitExceededException {
        validateLogin(login);
        validatePassword(password, confirmPassword);
        addUser(login, password);
    }

    @Override
    public void login(String login, String password) throws AuthException {
        for (User value : database) {
            if (value != null && login.equals(value.getLogin()) && password.hashCode() == value.getPasswordHash()) {
                System.out.println("Authorization [" + login + "] successes ");
                return;
            }
        }
        throw new AuthException("Authorization failed");
    }

    private void addUser(String login, String password) throws UserLimitExceededException {
        if (counter == limit) {
            throw new UserLimitExceededException("User limit exceeded. User limit " + limit);
        }
        this.database[counter++] = new User(login, password.hashCode());
        System.out.println("User [" + login + "] registered.");
    }

    private void validateLogin(String login) throws WrongLoginException {
        char[] log = login.toCharArray();

        if (log.length < 4 || log.length > 20) {
            throw new WrongLoginException("Login length is " + login.length() + "Login should be more then 3 and less then 20 symbols");
        }

        for (char c : log) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                throw new WrongLoginException("Login " + login + " has wrong char " + c);
            }
        }

        for (User value : database) {
            if (value != null && login.equals(value.getLogin())) {
                throw new WrongLoginException("User " + login + " already exceeded.");
            }
        }
    }

    private void validatePassword(String password, String confirmPassword) throws WrongPasswordException {
        char[] pass = password.toCharArray();
        if (pass.length < 10 || pass.length > 20) {
            throw new WrongPasswordException("Password length is " + pass.length + ". Password should be more than 10 and less than 20.");
        }

        for (char c : pass) {
            if (!Character.isLetterOrDigit(c) && c != '_' && c != '@' && c != '$') {
                throw new WrongPasswordException("Password have wrong char: " + c);
            }
        }

        if (password.hashCode() != confirmPassword.hashCode()) {
            throw new WrongPasswordException("Password don't match with confirm password.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return limit == service.limit && counter == service.counter && Objects.equals(user, service.user) && Objects.deepEquals(database, service.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, user, Arrays.hashCode(database), counter);
    }
}
