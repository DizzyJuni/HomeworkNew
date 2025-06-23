package src.main.java.Lesson_12.task3;

import Lesson_12.task3.Service;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {
    private Service service;
    private final String TEST_LOGIN = "admin_1234";
    private final String TEST_PASSWORD = "1234567890";
    private final int limit = 2;

    @BeforeEach
    void register() throws Exception {
        service = new Service(limit);
        service.register(TEST_LOGIN, TEST_PASSWORD, TEST_PASSWORD);
    }

    @Test
    @DisplayName("Достигнут лимит пользователей")
    void userLimitExceeded() throws Lesson_8.Exception.UserLimitExceededException, Lesson_8.Exception.WrongLoginException, Lesson_8.Exception.WrongPasswordException {
        for (int i = 1; i < limit; i++) {
            service.register("admin" + i, "1234567890", "1234567890");
        }
        assertThrows(Lesson_8.Exception.UserLimitExceededException.class, () -> service.register("admin_4321", TEST_PASSWORD, TEST_PASSWORD));
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка метода логин")
    @CsvSource(
            {
                    "Не правильный пароль, admin_1234, wrongpassword,",
                    "Не правильный логин, wronglogin, 1234567890",
                    "Пустой логин,'' ,1234567890",
                    "Пустой пароль, admin_1234, ''",
            }
    )
    void loginTestCsv(String testName, String login, String password) {
        assertThrows(Lesson_8.Exception.AuthException.class, () -> service.login(login, password));
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка метода регистрации для логина")
    @CsvSource(
            {
                    "Логин меньше 4 символов, 123, 1234567890, 1234567890",
                    "Логин больше 20 символов, 123456789012345678901, 1234567890, 1234567890",
                    "Запрещенные символы в логине ,admin_1234!@#$% ,1234567890, 1234567890",
                    "Логин занят, admin_1234, 1234567890, 1234567890",
                    "Пустой логин,'', 123, 123",
            }
    )
    void registerLoginTestCsv(String testName, String login, String password, String confirmPassword) {
        assertThrows(Lesson_8.Exception.WrongLoginException.class, () -> service.register(login, password, confirmPassword));
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка метода регистрации для пароля")
    @CsvSource(
            {
                    "Пароль меньше 10 символов, admin_12345, 123, 123",
                    "Пароль больше 20 символов, admin_12345, 123456789012345678901, 123456789012345678901",
                    "Запрещенные символы в пароле, admin_12345, 1234567890!№%:, 1234567890!№%:",
                    "Пароли не совпадают, admin_12345, 123, 1234",
                    "Пустой пароль, admin_12345, '', ''",
            }
    )
    void registerPasswordTestCsv2(String testName, String login, String password, String confirmPassword) {
        assertThrows(Lesson_8.Exception.WrongPasswordException.class, () -> service.register(login, password, confirmPassword));
    }

    @Test
    @DisplayName("Успешный логин")
    void loginTest() {
        assertDoesNotThrow(() -> service.login(TEST_LOGIN, TEST_PASSWORD));
    }

    @Test
    @DisplayName("Успешная регистрация")
    void registerSuccessTest(){
        assertDoesNotThrow(()-> service.register("user_1234", "1234567890", "1234567890"));
    }
}
