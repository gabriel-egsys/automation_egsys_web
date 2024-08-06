package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import PageElements.MapeElements;
import pages.BasePage;
import pages.OcorrenciaPage;

public class OcorrenciaStep {

    private WebDriver driver;
    private BasePage basePage;
    private OcorrenciaPage ocorrenciaPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        ocorrenciaPage = new OcorrenciaPage(driver);
    }

    @Given("que eu acesso o BackOffice")
    public void queEuAcesso() {
        basePage.acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
    }

    @When("eu preencho o campo {string} com {string}")
    public void queEuAcessoPreencho(String seletor, String texto) {
        basePage.preencherCampo(seletor, texto);
    }

    @When("eu clico em {string}")
    public void clicoEm(String texto) {
        basePage.clicarElemento(texto);
    }

    @When("eu seleciono {string} no campo {string}")
    public void euSelecionoNoCampo(String seletor, String texto) {
        basePage.selecionarOpcao(texto, seletor);
    }

    // @After
    // public void finalizar() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }
}