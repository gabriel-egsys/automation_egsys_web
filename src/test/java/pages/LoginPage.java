package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public static By RG_FIELD = By.id("rg");
    public static By PASSWORD_FIELD = By.id("password");
    public static By DIGITO_FIELD = By.id("digito");
    public static By LOGIN_BUTTON = By.id("entrar");
    public static By SELECIONAR_BUTTON = By.id("Selecionar");
    public static By MODAL_HEADER_TEXT = By.cssSelector("div.modal-header h4");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

}