package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageElements.MapeElements;

public class OcorrenciaPage {
    WebDriver driver;
    BasePage basePage;

    public OcorrenciaPage(WebDriver driver) {
        this.driver = driver;
        basePage = new BasePage(driver);
    }

    public void preencherOcorrencia(String elemento, String texto) {
        driver.switchTo().frame("iframeContainer");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        By campo = MapeElements.getSeletorPorNome(elemento);

        if (campo != null) {
            WebElement campoElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(campo));
            campoElemento.clear(); 
            campoElemento.sendKeys(texto);
        } else {
            throw new IllegalArgumentException("Nome do campo não encontrado: " + elemento);
        }

        driver.switchTo().defaultContent();
    }

    public void selecionarMunicipio(String municipio) {
        driver.switchTo().frame("iframeContainer");

        WebElement campoMunicipio = driver.findElement(MapeElements.getSeletorPorNome("CAMPO_MUNICIPIO"));
        campoMunicipio.sendKeys(municipio);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//datalist[@id='municipio_endereco_list']//option[text()='" + municipio + "']"), municipio));

        driver.switchTo().defaultContent();
    }
}