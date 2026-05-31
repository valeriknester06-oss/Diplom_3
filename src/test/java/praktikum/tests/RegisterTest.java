package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации нового пользователя")
    public void successfulRegisterTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        String email = UserGenerator.generateEmail();

        registerPage.register(
                "TestUser",
                email,
                "123456"
        );

        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    @Description("Проверка отображения ошибки при вводе короткого пароля")
    public void incorrectPasswordRegisterTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.register(
                "TestUser",
                UserGenerator.generateEmail(),
                "12345"
        );

        Assert.assertTrue(registerPage.isIncorrectPasswordVisible());
    }
}