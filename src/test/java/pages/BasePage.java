package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageElements.LoginElements;

public class BasePage {

    WebDriver driver;

    // Construtor para inicializar o WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void realizarLogin(){
        acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
        preencherCampo("administrador_sistema_demonstracao", LoginElements.RG_FIELD);
        preencherCampo("sade*103020", LoginElements.PASSWORD_FIELD);
        preencherCampo("", LoginElements.DIGITO_FIELD);
        clicarBotao(LoginElements.LOGIN_BUTTON);
        boolean urlCorreta = validarUrlAtual("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/usuarioAcesso");
        assertTrue("A URL esperada não foi encontrada.", urlCorreta);
        clicarBotao(LoginElements.SELECIONAR_BUTTON);
        boolean urlHome = validarUrlAtual("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/usuarioAcesso");
        assertTrue("A URL esperada não foi encontrada.", urlHome);
    }

    public void acessarPagina(String url) {
        driver.get(url);
    }

    public void preencherCampo(String texto, By campo) {
        WebElement campoElemento = driver.findElement(campo);
        campoElemento.sendKeys(texto);
    }

    public void clicarBotao(By botao) {
        WebElement button = driver.findElement(botao);
        button.click();
    }

    public boolean validarTexto(By seletor, String textoEsperado) {
        WebElement elemento = driver.findElement(seletor);
        String textoAtual = elemento.getText();
        return textoAtual.equals(textoEsperado);
    }

    public boolean validarUrlAtual(String urlEsperada) {
        String urlAtual = driver.getCurrentUrl();
        return urlAtual.equals(urlEsperada);
    }
}
