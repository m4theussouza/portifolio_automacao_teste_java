#language:pt
@Login
Funcionalidade: Realizar Login
  Esquema do Cenário: CT0001 Fazer login com diferentes usuarios SWAGLABS
  	Dado que eu esteja na página do SWAGLABS
  	Quando eu fizer login com o <usuario> e <senha>
  	Então valido que o login teve status <status>
  	
 	 	Exemplos:
 	 	|usuario									| senha					| status 			|
 	 	|"standard_user"					|"secret_sauce"	|	"sucesso"		|
 	 	|"locked_out_user"				|"secret_sauce"	|"bloqueado"	|
 	 	|"problem_user"						|"secret_sauce"	|"problema"		|
		|"performance_glitch_user"|"secret_sauce"	|"performance"|
