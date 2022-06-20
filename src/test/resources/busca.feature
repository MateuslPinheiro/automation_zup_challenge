#language: pt
@busca
Funcionalidade: Testar a busca por uma carta

  Contexto:
    Dado que entro no site da Dominaria

@fluxoBaseBusca
  Cenario: Busca por um nome comum
    Quando faco uma busca por "Island"
    Entao visualizo os produtos

@fluxoAlternativoBusca
  Cenario: Busca por um nome especifico
    Quando faco uma busca por "Autoridade da Regente"
    Entao visualizo a pagina da carta

@fluxosDeExcecoesBusca @fluxoDeExcecao01Busca
  Cenario: Busca por nada
    Quando faco uma busca por ""
    Entao visualizo a pagina inicial

@fluxosDeExcecoesBusca @fluxoDeExcecao02Busca
  Cenario: Busca por palavras inexistentes
    Quando faco uma busca por "d#k"
    Entao visualizo a mensagem "Nenhum item encontrado com os filtros utilizados."

@fluxosDeExcecoesBusca @fluxoDeExcecao03Busca
  Cenario: Busca por espaço
    Quando faco uma busca por " "
    Entao visualizo a pagina de busca avancada
