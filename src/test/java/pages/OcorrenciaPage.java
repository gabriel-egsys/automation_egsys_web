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
        // Alterna para o iframe correto
        driver.switchTo().frame("iframeContainer");

        // Espera explícita para garantir que o elemento esteja visível
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By campo = MapeElements.getSeletorPorNome(elemento);

        if (campo != null) {
            WebElement campoElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(campo));
            // Preencher o campo após garantir que o elemento está visível
            campoElemento.clear(); // Limpa o campo antes de preencher
            campoElemento.sendKeys(texto);
        } else {
            throw new IllegalArgumentException("Nome do campo não encontrado: " + elemento);
        }

        driver.switchTo().defaultContent();
    }

    public void selecionarMunicipio(String municipio) {
        driver.switchTo().frame("iframeContainer");

        // Localiza o campo de município e preenche com o valor desejado
        WebElement campoMunicipio = driver.findElement(MapeElements.getSeletorPorNome("CAMPO_MUNICIPIO"));
        campoMunicipio.sendKeys(municipio);

        // Aguarda a opção aparecer na lista (se necessário) e a seleciona
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//datalist[@id='municipio_endereco_list']//option[text()='" + municipio + "']"), municipio));

        driver.switchTo().defaultContent();
    }
}