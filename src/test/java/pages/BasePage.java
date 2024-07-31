package pages;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageElements.MapeElements;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void realizarLogin() {
        acessarPagina("https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login");
        preencherCampo("administrador_sistema_demonstracao", MapeElements.getSeletorPorNome("RG_FIELD"));
        preencherCampo("sade*103020", MapeElements.getSeletorPorNome("PASSWORD_FIELD"));
        preencherCampo("", MapeElements.getSeletorPorNome("DIGITO_FIELD"));
        clicarBotao(true, MapeElements.getSeletorPorNome("LOGIN_BUTTON"));
        boolean urlCorreta = validarUrlAtual(
                "https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/usuarioAcesso");
        assertTrue("A URL esperada não foi encontrada.", urlCorreta);
        clicarBotao(true, MapeElements.getSeletorPorNome("SELECIONAR_BUTTON"));
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

    public void clicarBotao(Boolean isTelaLogin, By botaoLocator) {
        if (isTelaLogin) {
            WebElement elemento = driver.findElement(botaoLocator);
            elemento.click();
        } else {
            driver.switchTo().frame("iframeContainer");
            WebElement elemento = driver.findElement(botaoLocator);
            elemento.click();
            driver.switchTo().defaultContent();
        }
    }

    public void clicarBotao(String botaoId) {
        try {
            WebElement elemento = driver.findElement(MapeElements.getSeletorPorNome(botaoId));
            elemento.click();
        } catch (NoSuchElementException e) {
            try {
                driver.switchTo().frame("iframeContainer");
                WebElement elementoNoFrame = driver.findElement(MapeElements.getSeletorPorNome(botaoId));
                elementoNoFrame.click();
                driver.switchTo().defaultContent();
            } catch (NoSuchElementException ex) {
                driver.switchTo().defaultContent();
                throw new NoSuchElementException("Elemento não encontrado dentro ou fora do frame: " + botaoId);
            }
        }
    }
    

    public boolean validarTexto(String textoEsperado) {
        String pageSource = driver.getPageSource();
        return pageSource.contains(textoEsperado);
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
        aguarde(1000);
        inputElemento.sendKeys(Keys.ARROW_DOWN);
        inputElemento.sendKeys(Keys.ENTER);
        aguarde(1000);
        driver.switchTo().defaultContent();
    }

    public void clicarBotaoPorNome(String tipoSeletor, String seletor) {
        By by;

        switch (tipoSeletor) {
            case "id":
                by = By.id(seletor);
                break;
            case "css":
                by = By.cssSelector(seletor);
                break;
            case "class":
                by = By.className(seletor);
                break;
            case "xpath":
                by = By.xpath(seletor);
                break;
            default:
                throw new IllegalArgumentException("Tipo de seletor não suportado: " + tipoSeletor);
        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        driver.switchTo().frame("iframeContainer");
        element.click();
    }

    public void aguarde(int tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}