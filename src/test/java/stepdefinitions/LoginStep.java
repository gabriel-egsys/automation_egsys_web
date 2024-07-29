package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import PageElements.LoginElements;

import static org.junit.Assert.assertTrue;

public class LoginStep {

    WebDriver driver;
    BasePage basePage;

    @Given("que eu acesso o BackOffice")
    public void queEuAcesso() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        basePage = new BasePage(driver); // Inicialize o BasePage com o driver
        basePage.acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
    }

    @When("eu preencho os campos corretamente")
    public void euPreenchoOsCamposCorretamente() {
        basePage.preencherCampo("administrador_sistema_demonstracao", LoginElements.RG_FIELD);
        basePage.preencherCampo("sade*103020", LoginElements.PASSWORD_FIELD);
        basePage.preencherCampo("", LoginElements.DIGITO_FIELD);
    }

    @When("eu clico em {string}")
    public void euClicoEm(String botao) {
        basePage.clicarBotao(LoginElements.LOGIN_BUTTON); // Utilize LoginElements para referenciar o botão
    }

    @Then("o login deve ser realizado")
    public void validaLogin() {
        boolean urlCorreta = basePage.validarUrlAtual("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/usuarioAcesso");
        assertTrue("A URL esperada não foi encontrada.", urlCorreta);
    }
    
    @After
    public void finalizar() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}