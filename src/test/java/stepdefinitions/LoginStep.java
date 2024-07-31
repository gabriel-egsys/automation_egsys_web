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

    @Given("que eu acesso o BackOffice")
    public void queEuAcesso() {
        basePage.acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
    }

    @When("eu preencho os campos corretamente")
    public void euPreenchoOsCamposCorretamente() {
        basePage.preencherCampo("administrador_sistema_demonstracao", LoginPage.RG_FIELD);
        basePage.preencherCampo("sade*103020", LoginPage.PASSWORD_FIELD);
        basePage.preencherCampo("", LoginPage.DIGITO_FIELD);
    }

    @When("eu clico em entrar")
    public void euClicoEm() {
        basePage.clicarBotao(true, MapeElements.getSeletorPorNome("LOGIN_BUTTON"));
    }

    @Then("o login deve ser realizado")
    public void validaLogin() {
        boolean urlCorreta = basePage
                .validarUrlAtual("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/usuarioAcesso");
        assertTrue("A URL esperada não foi encontrada.", urlCorreta);
    }

    @After
    public void finalizar() {
    if (driver != null) {
    driver.quit();
    }
    }
}