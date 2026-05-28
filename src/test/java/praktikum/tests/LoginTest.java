package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private String email;
    private final String password = "123456";

    private void registerUser() {

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

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("login"));
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт")
    public void loginFromMainPageTest() {

        registerUser();

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        loginPage.login(email, password);

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    public void loginFromPersonalAccountTest() {

        registerUser();

        driver.get("https://stellarburgers.education-services.ru/");

        MainPage mainPage = new MainPage(driver);

        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(email, password);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(
                        "https://stellarburgers.education-services.ru/"
                ));

        Assert.assertTrue(mainPage.isConstructorTitleVisible());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    public void loginFromRegisterFormTest() {

        registerUser();

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
    public void loginFromRestorePasswordFormTest() {

        registerUser();

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