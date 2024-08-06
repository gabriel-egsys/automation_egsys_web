package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import PageElements.MapeElements;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarPagina(String url) {
        driver.get(url);
    }

    public void clicarElemento(String nomeElemento) {
        By seletor = MapeElements.getSeletorPorNome(nomeElemento);
        if (seletor == null) {
            throw new IllegalArgumentException("Elemento com nome '" + nomeElemento + "' não encontrado.");
        }
        try {
            WebElement elemento = driver.findElement(seletor);
            elemento.click();
        } catch (NoSuchElementException e) {
            driver.switchTo().frame("iFrameContainer");
            WebElement elementoDentroIframe = driver.findElement(seletor);
            elementoDentroIframe.click();
            driver.switchTo().defaultContent();
        }
    }

    public void preencherCampo(String nomeCampo, String texto) {
        By seletor = MapeElements.getSeletorPorNome(nomeCampo);
        if (seletor == null) {
            throw new IllegalArgumentException("Campo com nome '" + nomeCampo + "' não encontrado.");
        }
        try {
            switchToIframeIfPresent("iframeContainer");
            Thread.sleep(1000); 
            WebElement campoDentroIframe = driver.findElement(seletor);
            campoDentroIframe.clear();
            campoDentroIframe.sendKeys(texto);
            driver.switchTo().defaultContent(); 
        } catch (NoSuchFrameException | NoSuchElementException | InterruptedException e) {
            try {
                Thread.sleep(1000); 
                WebElement campo = driver.findElement(seletor);
                campo.clear();
                campo.sendKeys(texto);
            } catch (NoSuchElementException | InterruptedException ex) {
                throw new RuntimeException("Elemento não encontrado: " + ex.getMessage());
            }
        }
    }
    

    private void switchToIframeIfPresent(String iframeName) {
        try {
            driver.switchTo().frame(iframeName);
        } catch (NoSuchFrameException e) {
            System.out.println("Iframe com nome '" + iframeName + "' não encontrado.");
        }
    }

    public void preencheCampo(String nomeCampo, String texto){
        By seletor = MapeElements.getSeletorPorNome(nomeCampo);

        driver.switchTo().frame("iframeContainer");
        WebElement campoDentroIframe = driver.findElement(seletor);
        campoDentroIframe.clear();
        campoDentroIframe.sendKeys(texto);
        driver.switchTo().defaultContent();
    }

    public void selecionarOpcao(String nomeCampo, String texto) {
        By seletor = MapeElements.getSeletorPorNome(nomeCampo);
        if (seletor == null) {
            throw new IllegalArgumentException("Campo select com nome '" + nomeCampo + "' não encontrado.");
        }
    
        try {
            realizarSelecao(nomeCampo, texto);
        } catch (NoSuchElementException e) {
            driver.switchTo().frame("iFrameContainer");
            try {
                realizarSelecao(nomeCampo, texto);
            } finally {
                driver.switchTo().defaultContent();
            }
        }
    }
    
    private void realizarSelecao(String nomeCampo, String texto) {
        try {
            preencheCampo(nomeCampo, texto);
            Thread.sleep(1300); 
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            actions.sendKeys(Keys.ENTER).perform();
            Thread.sleep(1300); 
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrompida", ex);
        }
    }
 
}