Feature: Calculadora
  Para evitar erros de cálculo
  Como um usuário
  Eu quero somar dois números

  Scenario: Somar dois números positivos
    Given eu tenho a calculadora ligada
    When eu somo 2 e 3
    Then o resultado deve ser 5
