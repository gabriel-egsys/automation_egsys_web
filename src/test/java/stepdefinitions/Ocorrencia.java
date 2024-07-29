package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import static org.junit.Assert.assertTrue;


public class Ocorrencia {
    
    WebDriver driver;
    BasePage basePage;

    @Given("que eu realizo login no sade")
    public void queEuAcesso() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        basePage = new BasePage(driver); 
        basePage.acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
        basePage.realizarLogin();
    }

    @After
    public void finalizar() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}
