package praktikum.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {

        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();

        if (browser.equalsIgnoreCase("yandex")) {

            options.addArguments("--remote-allow-origins=*");

            // browser.exe НЕ указываем
        }

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://stellarburgers.education-services.ru/");
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}