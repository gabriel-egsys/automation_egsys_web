Feature: Ocorrencia

  Background: Realizar login no SADE
    Given que eu realizo login no sade

  Scenario: Gerar nova ocorrência
    When eu clico no menu 'GERAR_OCORRENCIA_LINK'
    And eu preencho o campo "CAMPO_NOME" com "AutomaçãoQA"
    And eu preencho o campo "CAMPO_TELEFONE" com "51999999999"
    And eu seleciono "Curitiba" no campo "CAMPO_MUNICIPIO"
    And eu seleciono "Centro" no campo "CAMPO_BAIRRO"
    And eu seleciono "AVENIDA BRASILIA" no campo "CAMPO_ENDERECO"
    And eu preencho o campo "CAMPO_NUMERO_ENDERECO" com "221"
    And eu preencho o campo "CAMPO_DESCRICAO" com "Ocorrência Gerada Pela Automação."
    And eu seleciono "ROUBO" no campo "CAMPO_NATUREZA"
    And eu clico em "AUTOR_NAO_ESTA_NO_LOCAL"
    And eu clico em "AUTOR_NAO_ESTA_ARMADO"
    And eu clico em "NAO_RISCO_DE_TUMULTO"
    And eu clico em "NAO_PESSOAS_FERIDAS_RISCO_DE_MORTE"
    When eu clico em "BOTAO_SALVAR"
    Then deve ser exibido "Ocorrência registrada"