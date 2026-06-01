package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import praktikum.api.UserClient;
import praktikum.model.LoginUser;
import praktikum.model.User;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.utils.BaseTest;
import praktikum.utils.UserGenerator;

public class RegisterTest extends BaseTest {

    private final UserClient userClient = new UserClient();

    private String accessToken;

    @After
    public void deleteUser() {

        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации нового пользователя")
    public void successfulRegisterTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        User user = UserGenerator.generateUser();

        registerPage.register(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );

        ValidatableResponse response = userClient.loginUser(
                new LoginUser(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        accessToken = response.extract().path("accessToken");

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