Feature: Login

  Scenario: Realizar login com sucesso
    Given que eu acesso o "https://treinamento.harpya.pm.pr.gov.br/pmpr/syspm-web/public/login"
    And eu preencho os campos corretamente
    When eu clico em "entrar"
    # Then o login deve ser realizado com sucesso