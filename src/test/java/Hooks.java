import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.OcorrenciaPage;

public class Hooks {
    private static WebDriver driver;
    private static BasePage basePage;
    private static OcorrenciaPage ocorrenciaPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        ocorrenciaPage = new OcorrenciaPage(driver);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static BasePage getBasePage() {
        return basePage;
    }

    public static OcorrenciaPage getOcorrenciaPage() {
        return ocorrenciaPage;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}