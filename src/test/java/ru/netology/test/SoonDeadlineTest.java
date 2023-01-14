package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.date.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.date.DataHelper.*;

public class SoonDeadlineTest {

    @BeforeAll
    public static void setupDb() {
        DataHelper.cleanDb();
    }
    @BeforeEach
    public void setup() {

        generateUser();
    }

    @Test
    public void correctInput() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var user = getUserData();
        var verificationPage = loginPage.logInn(user.getLogin(), "qwerty123");
        var cod = getCod(user);
        verificationPage.codeInput(cod);
    }

    @Test
    public void blockedInput() {
        open("http://localhost:9999");
        var user = getUserData();
        var loginPage = new LoginPage();
        loginPage.tripleIncorrecItnput(user.getLogin(), getIncorrectPassword());
    }
}