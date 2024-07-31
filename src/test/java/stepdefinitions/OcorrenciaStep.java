package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

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

    @Given("que eu realizo login no sade")
    public void queEuAcesso() {
        basePage.acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
        basePage.realizarLogin();
    }

    @When("eu clico no menu {string}")
    public void acessaMenuGerarOcorrencia(String selector) {
        WebElement elemento = driver.findElement(MapeElements.getSeletorPorNome(selector));
        elemento.click();
    }

    @When("eu preencho o campo {string} com {string}")
    public void preencheDadosOcorrencia(String elemento, String texto) {
        ocorrenciaPage.preencherOcorrencia(elemento, texto);
    }

    @When("eu seleciono {string} no campo {string}")
    public void selecionaMunicipio(String texto, String municipio) {
        basePage.selecionarOpcaoDatalist(municipio, texto);
    }

    @When("eu clico em {string}")
    public void eu_clico_no(String botao) {
        basePage.clicarBotao(false, botao);
    }

    @Then("deve ser exibido {string}")
    public void deve_ser_exibido(String texto) {
        basePage.validarTexto(texto);
    }

    @After
    public void finalizar() {
        if (driver != null) {
            driver.quit();
        }
    }
}