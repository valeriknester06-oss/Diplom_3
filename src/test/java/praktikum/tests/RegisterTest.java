package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

import java.time.Duration;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
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

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("login"));

        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
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
