#language: pt
Funcionalidade: Buscando um cep 

  Esquema do Cenario: Teste cep válido  
    Dado que eu utilizo o cep  "<cep>" na minha busca 
    Quando faco a requisicao para buscar informacoes de um cep
    Entao o codigo de retorno deve ser <codigo>
    E verifico que o body da reposta da api retornou "<cep>","<logradouro>","<cidade>","<estado>","<bairro>"
    Exemplos: 
     |cep       | codigo   | cidade             | estado  |logradouro    	 	 |bairro            |
     | 13326050 |   200    |	 Salto	          |  SP	 	  |Rua Bruxelas	 		 |Jardim Elizabeth	|
     | 23073420 |   200    |	 Rio de Janeiro	  |  RJ		  |Rua Abil da Silva |Campo Grande     	|

    Esquema do Cenario: Teste cep inválido
    Dado que eu utilizo o cep  "<cep>" na minha busca 
    Quando faco a requisicao para buscar informacoes de um cep
    Entao o codigo de retorno deve ser <codigo>
    Exemplos: 
     |cep           | codigo   |
     | a      			|      404 |
    
    
   
     	
     	
     	