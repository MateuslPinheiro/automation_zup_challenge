#language: pt
@carrinho
Funcionalidade: Testar a compra de uma unidade com estoque
Contexto:
Dado que estou em uma página de uma carta com estoque

Cenário: Compra de 1 unidade
Quando compro 1 unidade
E acesso o carrinho
Então visualizo o carrinho com uma carta

Cenário: Compra de 0 unidades
Quando compro 0 unidades
Então recebo a mensagem "É necessário preencher uma quantidade válida para adicionar o item ao seu carrinho."
Quando acesso o carrinho
Então visualizo a mensagem "Ops! Seu carrinho está vazio."

Cenário: Compra de x unidades tal que x>estoque
Quando compro o dobro de unidades que meu estoque permite
E acesso o carrinho
Então visualizo que há x unidades

Cenário: Compra de x unidades tal que x=estoque
Quando compro exatamente o número de unidades do meu estoque
E acesso o carrinho
Então visualizo que há x unidades