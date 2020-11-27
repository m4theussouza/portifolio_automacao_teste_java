#language:pt
@Login
Funcionalidade: Realizar Login
  Cenário: CT0001 Fazer login com standard_user SWAGLABS
  	Dado que eu esteja na página do SWAGLABS
  	Quando eu fizer login com o "standard_user" e "secret_sauce"
  	Então valido que o login teve status "sucesso"
  
  Cenário: CT0002 Fazer login com locked_out_user SWAGLABS
  	Dado que eu esteja na página do SWAGLABS
  	Quando eu fizer login com o "locked_out_user" e "secret_sauce"
  	Então valido que o login teve status "bloqueado"
  
  Cenário: CT0003 Fazer login com problem_user SWAGLABS
  	Dado que eu esteja na página do SWAGLABS
  	Quando eu fizer login com o "problem_user" e "secret_sauce"
  	Então valido que o login teve status "problema"
  
  Cenário: CT0004 Fazer login com performance_glitch_user SWAGLABS
  	Dado que eu esteja na página do SWAGLABS
  	Quando eu fizer login com o "performance_glitch_user" e "secret_sauce"
  	Então valido que o login teve status "performance"
  	
  	
  # Poderia ter usada a tabela abaixo, porem os pdf's seriam gerados todos com o mesmo nome	
  # pois utilizo do nome do cenario para gerar o nome da pasta onde ficara o pdf
 	# 	Exemplos:
 	# 	|usuario									| senha					| status 			|
 	# 	|"standard_user"					|"secret_sauce"	|	"sucesso"		|
 	#		|"locked_out_user"				|"secret_sauce"	|"bloqueado"	|
 	# 	|"problem_user"						|"secret_sauce"	|"problema"		|
	#		|"performance_glitch_user"|"secret_sauce"	|"performance"|
