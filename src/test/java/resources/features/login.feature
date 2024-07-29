Feature: Login

  Scenario: Realizar login com sucesso
    Given que eu acesso o BackOffice
    And eu preencho os campos corretamente
    When eu clico em "entrar"
    Then o login deve ser realizado