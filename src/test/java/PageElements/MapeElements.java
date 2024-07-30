package PageElements;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;

public class MapeElements {
    private static final Map<String, By> SELETORES = new HashMap<>();

    static {
        // Mapeamento elementos LoginPage
        SELETORES.put("RG_FIELD", Elements.RG_FIELD);
        SELETORES.put("PASSWORD_FIELD", Elements.PASSWORD_FIELD);
        SELETORES.put("DIGITO_FIELD", Elements.DIGITO_FIELD);
        SELETORES.put("LOGIN_BUTTON", Elements.LOGIN_BUTTON);
        SELETORES.put("SELECIONAR_BUTTON", Elements.SELECIONAR_BUTTON);
        SELETORES.put("MODAL_HEADER_TEXT", Elements.MODAL_HEADER_TEXT);

        // Mapeamento elementos OcorrenciaPage
        SELETORES.put("CAMPO_NOME", Elements.CAMPO_NOME);
        SELETORES.put("CAMPO_TELEFONE", Elements.CAMPO_TELEFONE);
        SELETORES.put("CAMPO_MUNICIPIO", Elements.CAMPO_MUNICIPIO);
        SELETORES.put("CAMPO_BAIRRO", Elements.CAMPO_BAIRRO);
        SELETORES.put("CAMPO_DESCRICAO", Elements.CAMPO_DESCRICAO);
        SELETORES.put("CAMPO_ENDERECO", Elements.CAMPO_ENDERECO);
        SELETORES.put("CAMPO_NATUREZA", Elements.CAMPO_NATUREZA);
        SELETORES.put("AUTOR_NAO_ESTA_NO_LOCAL", Elements.AUTOR_NAO_ESTA_NO_LOCAL);
        SELETORES.put("AUTOR_NAO_ESTA_ARMADO", Elements.AUTOR_NAO_ESTA_ARMADO);
    }

    public static By getSeletorPorNome(String nome) {
        return SELETORES.get(nome);
    }
}
