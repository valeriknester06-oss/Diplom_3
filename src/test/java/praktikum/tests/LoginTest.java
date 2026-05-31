package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

public class LoginTest extends BaseTest {

    private String email;
    private final String password = "123456";

    @Before
    public void registerUser() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

        email = UserGenerator.generateEmail();

        registerPage.register(
                "TestUser",
                email,
                password
        );

        Assert.assertTrue(loginPage.isLoginPageOpened());
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    @Description("Проверка авторизации пользователя через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        loginPage.login(email, password);

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    @Description("Проверка авторизации пользователя через кнопку 'Личный кабинет'")
    public void loginFromPersonalAccountTest() {
        driver.get("https://stellarburgers.education-services.ru/");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверка авторизации пользователя через ссылку 'Войти' на странице регистрации")
    public void loginFromRegisterFormTest() {
        driver.get("https://stellarburgers.education-services.ru/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(email, password);

        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверка авторизации пользователя через ссылку 'Войти' на странице восстановления пароля")
    public void loginFromRestorePasswordFormTest() {
        driver.get("https://stellarburgers.education-services.ru/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();

        ForgotPasswordPage forgotPasswordPage =
                new ForgotPasswordPage(driver);

        forgotPasswordPage.clickLoginLink();

        loginPage.login(email, password);

        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }
}