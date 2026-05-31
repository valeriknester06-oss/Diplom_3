package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput =
            By.xpath("//label[text()='Email']/following-sibling::input");

    private final By passwordInput =
            By.xpath("//label[text()='Пароль']/following-sibling::input");

    private final By loginButton =
            By.xpath("//button[text()='Войти']");

    private final By registerLink =
            By.xpath("//a[text()='Зарегистрироваться']");

    private final By restorePasswordLink =
            By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Авторизация пользователя")
    public void login(String email, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
                .sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Переход на страницу регистрации")
    public void clickRegisterLink() {

        wait.until(ExpectedConditions.elementToBeClickable(registerLink))
                .click();
    }

    @Step("Переход на страницу восстановления пароля")
    public void clickRestorePasswordLink() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        restorePasswordLink
                )
        ).click();
    }

    @Step("Проверка отображения кнопки входа")
    public boolean isLoginButtonVisible() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }

    @Step("Ожидание открытия страницы авторизации")
    public boolean isLoginPageOpened() {

        return wait.until(
                ExpectedConditions.urlContains("login")
        );
    }
}