package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.api.UserClient;
import praktikum.model.User;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

public class LoginTest extends BaseTest {

    private User user;
    private String accessToken;
    private final UserClient userClient = new UserClient();

    @Before
    public void createUser() {
        user = UserGenerator.generateUser();

        ValidatableResponse response = userClient.createUser(user);

        accessToken = response.extract().path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    @Description("Проверка авторизации пользователя через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    @Description("Проверка авторизации пользователя через кнопку 'Личный кабинет'")
    public void loginFromPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

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

        loginPage.login(user.getEmail(), user.getPassword());

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

        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }
}