package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageElements.MapeElements;

public class BasePage {

    WebDriver driver;

    // Construtor para inicializar o WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void realizarLogin() {
        acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
        preencherCampo("administrador_sistema_demonstracao", MapeElements.getSeletorPorNome("RG_FIELD"));
        preencherCampo("sade*103020", MapeElements.getSeletorPorNome("PASSWORD_FIELD"));
        preencherCampo("", MapeElements.getSeletorPorNome("DIGITO_FIELD"));
        clicarBotao(MapeElements.getSeletorPorNome("LOGIN_BUTTON"));
        boolean urlCorreta = validarUrlAtual(
                "https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/usuarioAcesso");
        assertTrue("A URL esperada não foi encontrada.", urlCorreta);
        clicarBotao(MapeElements.getSeletorPorNome("SELECIONAR_BUTTON"));
        boolean urlHome = validarUrlAtual("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/home");
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(botao));
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

    public void selecionarOpcaoDatalist(String inputId, String textoOpcao) {
        driver.switchTo().frame("iframeContainer");
        WebElement inputElemento = driver.findElement(MapeElements.getSeletorPorNome(inputId));
        inputElemento.clear();
        inputElemento.sendKeys(textoOpcao);
        inputElemento.sendKeys(Keys.ARROW_DOWN);
        aguarde(1000);
        inputElemento.sendKeys(Keys.ENTER);
        aguarde(1000);

        driver.switchTo().defaultContent();
    }

    public void aguarde(int tempo){
        try {
            Thread.sleep(tempo); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}