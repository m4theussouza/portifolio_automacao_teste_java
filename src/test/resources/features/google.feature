#language:pt
@google
Funcionalidade: Google
  @buscaGoogle
  Cenário: Buscar RSI no Google
  	Dado que eu esteja na página do Google
  	Quando eu buscar pela palavra "RSI Informática"
  	Então eu valido que a busca foi realizada