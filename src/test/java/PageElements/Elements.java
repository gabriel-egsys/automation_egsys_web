package PageElements;

import org.openqa.selenium.By;

public class Elements {
    // Elementos LoginPage
    public static final By RG_FIELD = By.id("rg");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By DIGITO_FIELD = By.id("digito");
    public static final By LOGIN_BUTTON = By.id("entrar");
    public static final By SELECIONAR_BUTTON = By.id("Selecionar");
    public static final By MODAL_HEADER_TEXT = By.cssSelector("div.modal-header h4");

    // Elementos OcorrenciaPage
    public static final By GERAR_OCORRENCIA_LINK = By.xpath("//li[a[text()='Gerar Ocorrência']]");
    public static final By CAMPO_NOME = By.id("NOME");
    public static final By CAMPO_TELEFONE = By.id("TELEFONE");
    public static final By CAMPO_MUNICIPIO = By.id("municipio_endereco");
    public static final By CAMPO_BAIRRO = By.id("pesquisa_bairro");
    public static final By CAMPO_DESCRICAO = By.id("DESCRICAO");
    public static final By CAMPO_ENDERECO = By.id("pesquisa_endereco");
    public static final By CAMPO_NATUREZA = By.id("CLASSIFICACAO_ATENDIMENTO");
    public static final By AUTOR_NAO_ESTA_NO_LOCAL = By.id("RESPOSTA_NIVEL_CRIME_0_0");
    public static final By AUTOR_NAO_ESTA_ARMADO = By.id("RESPOSTA_NIVEL_CRIME_1_0");
}