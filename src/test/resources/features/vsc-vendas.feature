#language:pt

Funcionalidade: Realizar Venda de Solucao de Capturas
  @CT0001
  Cenário: CT0001 PF, Novo Cliente, SKU25, Endereço Estabelecimento, Conta Bancaria, Crédito
  	Dado que eu esteja na página de Vendas, preencher dados e avançar
    E estiver na tela de Dados Cadastrais, preencher dados e avançar
    E estiver na tela de Endereco, preecher dados e avançar
    E estiver na tela de Domicilio Bancario, preecher dados e avançar
    E estiver na tela de Condicoes, aceitar condicoes e avançar
    E estiver na tela de Pagamento, preencher os dados e avançar


  @CT0002_Venda_PJ_Sucesso
  Cenário: CT0002 PF, Novo Cliente, SKU25, Endereço Estabelecimento, Conta Bancaria, Crédito
  	Dado que eu esteja na página de Vendas
  	#Quando eu fizer login com o "locked_out_user" e "secret_sauce"
  	#Então valido que o login teve status "bloqueado"
  
  #Cenário: CT0003 Fazer login com problem_user SWAGLABS
  #	Dado que eu esteja na página do SWAGLABS
  	#Quando eu fizer login com o "problem_user" e "secret_sauce"
  	#Então valido que o login teve status "problema"
  
  #Cenário: CT0004 Fazer login com performance_glitch_user SWAGLABS
  	#Dado que eu esteja na página do SWAGLABS
  	#Quando eu fizer login com o "performance_glitch_user" e "secret_sauce"
  	#Então valido que o login teve status "performance"
  	
  	
  # Poderia ter usada a tabela abaixo, porem os pdf's seriam gerados todos com o mesmo nome	
  # pois utilizo do nome do cenario para gerar o nome da pasta onde ficara o pdf
 	# 	Exemplos:
 	# 	|usuario									| senha					| status 			|
 	# 	|"standard_user"					|"secret_sauce"	|	"sucesso"		|
 	#		|"locked_out_user"				|"secret_sauce"	|"bloqueado"	|
 	# 	|"problem_user"						|"secret_sauce"	|"problema"		|
	#		|"performance_glitch_user"|"secret_sauce"	|"performance"|
