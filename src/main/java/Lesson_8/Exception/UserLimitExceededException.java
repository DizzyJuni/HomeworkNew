package Lesson_8.Exception;

public class UserLimitExceededException extends Exception {

    public UserLimitExceededException(String message) {
        super(message);
    }
}
