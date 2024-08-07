package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageElements.MapeElements;

public class BasePage {

    WebDriver driver;

    public class ElementoNaoClicavelException extends Exception {
        public ElementoNaoClicavelException(String message) {
            super(message);
        }
    
        public ElementoNaoClicavelException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarPagina(String url) {
        driver.get(url);
    }

    // public void clicarElemento(String nomeElemento) {
    //     By seletor = MapeElements.getSeletorPorNome(nomeElemento);
    //     if (seletor == null) {
    //         throw new IllegalArgumentException("Elemento com nome '" + nomeElemento + "' não encontrado.");
    //     }
    //     try {
    //         realizarClique(seletor);
    //     } catch (NoSuchElementException e) {
    //         switchToIframeIfPresent("iframeContainer");
    //         try {
    //             realizarClique(seletor);
    //         } finally {
    //             driver.switchTo().defaultContent(); 
    //         }
    //     }
    // }

    public void clicarElemento(String nomeElemento){
        try {
            clicarElementoForaDoFrame(nomeElemento);
        } catch (ElementoNaoClicavelException e) {
            System.err.println("Erro ao tentar clicar fora do frame: " + e.getMessage());
            e.printStackTrace();
            try {
                clicarElementoDentroDoFrame(nomeElemento);
            } catch (ElementoNaoClicavelException ex) {
                System.err.println("Erro ao tentar clicar dentro do frame: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public void clicarElementoForaDoFrame(String elemento) throws ElementoNaoClicavelException {
        By seletor = MapeElements.getSeletorPorNome(elemento);
        try {
            realizarClique(seletor);
        } catch (Exception e) {
            throw new ElementoNaoClicavelException("Não foi possível clicar no elemento: " + elemento + " fora do frame.", e);
        }
    }

    public void clicarElementoDentroDoFrame(String elemento) throws ElementoNaoClicavelException {
        By seletor = MapeElements.getSeletorPorNome(elemento);
        try {
            switchToIframeIfPresent("iframeContainer");
            realizarClique(seletor);
            driver.switchTo().defaultContent(); 
        } catch (Exception e) {
            throw new ElementoNaoClicavelException("Não foi possível clicar no elemento: " + elemento + " dentro do frame.", e);
        }
    }

    public void realizarClique(By seletor){
        WebElement elemento = driver.findElement(seletor);
        elemento.click();
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
            Thread.sleep(800); 
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            actions.sendKeys(Keys.ENTER).perform();
            Thread.sleep(1000); 
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrompida", ex);
        }
    }


    public void validaExibicao(String seletor, String textoEsperado) {
        By by = MapeElements.getSeletorPorNome(seletor);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // Espera até que o elemento esteja visível
            WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            // Verifica se o texto do elemento corresponde ao texto esperado
            if (elemento.getText().equals(textoEsperado)) {
                System.out.println("Modal exibido com o texto esperado: " + textoEsperado);
            } else {
                throw new AssertionError("Texto do modal não corresponde ao texto esperado. Encontrado: " + elemento.getText() + ", Esperado: " + textoEsperado);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar a exibição do modal: " + e.getMessage(), e);
        }
    }
 
}