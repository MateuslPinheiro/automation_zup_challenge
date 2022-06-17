#language: pt
@busca
Funcionalidade: Testar a busca por uma carta

  Contexto:
    Dado que entro no site da Dominária

  Cenário: Busca por um nome existente em várias cartas
    Quando faço uma busca por "Island"
    Então visualizo os produtos

  Cenário: Busca por exatamente o nome de uma carta cujo nome não contém em outras cartas
    Quando faço uma busca por "Autoridade da Regente"
    Então visualizo a página da carta

  Cenário: Busca por nada
    Quando faço uma busca por ""
    Então visualizo a página inicial

  Cenário: Busca por palavras inexistentes
    Quando faço uma busca por "d#k"
    Então visualizo a mensagem "Nenhum item encontrado com os filtros utilizados."

  Cenário: Busca por espaço
    Quando faço uma busca por " "
    Então visualizo a página de busca avançada
