package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorTitle = By.xpath("//h1[text()='Соберите бургер']");

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    private final By activeBunsTab =
            By.xpath("//div[contains(@class,'current')]//span[text()='Булки']");

    private final By activeSaucesTab =
            By.xpath("//div[contains(@class,'current')]//span[text()='Соусы']");

    private final By activeFillingsTab =
            By.xpath("//div[contains(@class,'current')]//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickPersonalAccountButton() {
        click(personalAccountButton);
    }

    public void clickBunsTab() {
        click(bunsTab);
    }

    public void clickSaucesTab() {
        click(saucesTab);
    }

    public void clickFillingsTab() {
        click(fillingsTab);
    }

    public boolean isConstructorTitleVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(constructorTitle)
        ).isDisplayed();
    }

    public boolean isBunsTabActive() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeBunsTab)
        ).isDisplayed();
    }

    public boolean isSaucesTabActive() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeSaucesTab)
        ).isDisplayed();
    }

    public boolean isFillingsTabActive() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeFillingsTab)
        ).isDisplayed();
    }
}
