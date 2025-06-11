package Lesson_12.task3;

import Lesson_8.Exception.AuthException;
import Lesson_8.Exception.UserLimitExceededException;
import Lesson_8.Exception.WrongLoginException;
import Lesson_8.Exception.WrongPasswordException;

public interface LoginService {
    void register(final String login, final String password, final String confirmPassword)
            throws WrongLoginException, WrongPasswordException, UserLimitExceededException;

    void login (final String login, final String password)
        throws AuthException;
}
