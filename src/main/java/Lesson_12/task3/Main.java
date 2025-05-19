package Lesson_12.task3;

import Lesson_8.Exception.AuthException;
import Lesson_8.Exception.UserLimitExceededException;
import Lesson_8.Exception.WrongLoginException;
import Lesson_8.Exception.WrongPasswordException;

public class Main {
    public static void main(String[] args) throws UserLimitExceededException, WrongPasswordException, WrongLoginException, AuthException {
        Service service = new Service(3);
        service.register("popo@", "1234567890", "1234567890");
        service.register("popop", "123456789_", "123456789_");
        service.register("popop0", "123456789_", "123456789_");
        service.login("popo", "1234567890");
    }
}
