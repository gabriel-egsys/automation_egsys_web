Feature: Ocorrencia

  Background: Realizar login no SADE
    Given que eu realizo login no sade

  Scenario: Gerar nova ocorrência
    When eu clico no menu 'Gerar Ocorrência'
    And eu preencho o campo "CAMPO_NOME" com "AutomaçãoQA"
    And eu preencho o campo "CAMPO_TELEFONE" com "51999999999"
    And eu seleciono "Curitiba" no campo "CAMPO_MUNICIPIO"
    And eu seleciono "Centro" no campo "CAMPO_BAIRRO"
    And eu seleciono "AVENIDA BRASILIA" no campo "CAMPO_ENDERECO"
    And eu preencho o campo "CAMPO_DESCRICAO" com "Ocorrência Gerada Pela Automação."
    # And eu seleciono "ROUBO" no campo "CAMPO_NATUREZA"
    And eu clico em "AUTOR_NAO_ESTA_NO_LOCAL"
    And eu clico em "AUTOR_NAO_ESTA_ARMADO"

    # And eu preencho o campo "CAMPO_MUNICIPIO" com "Curitiba"
    # And eu preencho o campo "CAMPO_BAIRRO" com "Centro"
    # And eu preencho o campo "CAMPO_ENDERECO" com "AVENIDA BRASILIA"
    # And eu preencho o campo "CAMPO_DESCRICAO" com "Ocorrência Gerada Pela Automação."