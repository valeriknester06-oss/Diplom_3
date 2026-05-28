package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import praktikum.pages.MainPage;
import praktikum.utils.BaseTest;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу Булки")
    public void bunsTabTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertTrue(mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void saucesTabTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        Assert.assertTrue(mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void fillingsTabTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsTab();

        Assert.assertTrue(mainPage.isFillingsTabActive());
    }
}