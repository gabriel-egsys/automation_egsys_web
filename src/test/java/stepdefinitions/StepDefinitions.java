package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    WebDriver driver;

    @Given("que eu acesso o {string}")
    public void queEuAcessoO(String site) {
        System.setProperty("webdriver.chrome.driver", "C://Users//gabri//IdeaProjects//egsys-automation//src//main//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(site);
    }

    @When("eu preencho os campos corretamente")
    public void euPreenchoOsCamposCorretamente() {
        // Encontre os elementos de input pelos seus IDs
        WebElement rgField = driver.findElement(By.id("rg"));
        WebElement senhaField = driver.findElement(By.id("password"));
        WebElement digitoField = driver.findElement(By.id("digito"));

        // Preencha os campos com os valores fornecidos
        rgField.sendKeys("administrador_sistema_demonstracao");
        senhaField.sendKeys("sade*103020");
        digitoField.sendKeys("");
    }

    @When("eu clico em {string}")
    public void euClicoEm(String botao) {
        WebElement button = driver.findElement(By.id(botao));
        button.click();
    }
}