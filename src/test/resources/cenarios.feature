#language: pt
Funcionalidade: Cart management
  Como um cliente
  eu gostaria de escolher produtos
  e adicionar ao meu carrinho

  Cenário:
    Dado que entro no site da Dominaria
    Quando faço uma busca por "Island"
    Então visualizo os produtos
    E filtro por produtos com estoque
    Quando seleciono um produto
    Então visualizo a página do produto
    Quando aperto em comprar
    E vejo meu carrinho
    Então verifico se o item está lá
