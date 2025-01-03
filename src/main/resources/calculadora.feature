Feature: Calculadora
  Para evitar erros de cálculo
  Como um usuário
  Eu quero somar dois números

  Scenario: Somar dois números positivos
    Given eu tenho a calculadora ligada
    When eu somo 2 e 3
    Then o resultado deve ser 5

  Scenario: Subtrair dois números positivos
    Given eu tenho a calculadora ligada
    When eu subtraio 2 de 3
    Then o resultado deve ser 1

Scenario: Pesquisar livros
  Given eu tenha os segintes livros na base de dados
    | title                                | author      |
    | The Devil in the White City          | Erik Larson |
    | The Lion, the Witch and the Wardrobe | C.S. Lewis  |
    | In the Garden of Beasts              | Erik Larson |
  When eu buscar pelo autor "Erik Larson"
  Then eu vou achar 2 livros