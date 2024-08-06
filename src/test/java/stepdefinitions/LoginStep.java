package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageElements.MapeElements;
import pages.BasePage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginStep {

    private WebDriver driver;
    private BasePage basePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
    }


    @After
    public void finalizar() {
    if (driver != null) {
    driver.quit();
    }
    }
}