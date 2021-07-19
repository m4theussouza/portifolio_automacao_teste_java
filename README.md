
# Projeto Teste Login SwagLab

Desafio para fazer login com alguns usuarios do SwagLab.

## Preparação do ambiente

- Download e Instalação
    - Baixe e instale o Java [JDK Java Download](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html); [Java Instalação](https://medium.com/@mauriciogeneroso/configurando-java-4-como-configurar-as-vari%C3%A1veis-java-home-path-e-classpath-no-windows-46040950638f).
    - Baixe e instale o [Eclipse](https://www.eclipse.org/downloads/) IDE.
    - Baixa o driver do Navegador que deseja executar o teste. [Chrome](https://sites.google.com/a/chromium.org/chromedriver/downloads); [Firefox](github.com/mozilla/geckodriver/releases); [IE](selenium-release.storage.googleapis.com/index.html);
    - Baixe e instale o Maven. [Maven Download](https://maven.apache.org/download.cgi); [Maven Instalação](https://dicasdejava.com.br/como-instalar-o-maven-no-windows)
    - Baixe e instale o Git para Windows. [Git Download](https://git-scm.com/download/win); 

## Baixando projeto via GitHub

- Siga as instruções abaixo:
    - Navegue até a pasta onde deseja deixar o projeto;
    - Clique com o botão direito em uma parte em branco da pasta;
    - E selecione 'Git Bash Here' uma janela como a do cmd irá abrir;
    - Coloque o seguinte comando no prompt sem as aspas ' git clone https://github.com/m4theussouza/portifolio_automacao_teste_java';
    - Pronto, os arquivos já estão no diretório.
    
## Arquivo start.properties
- Nesse arquivo que está localizado no diretório 'src\test\resources' temos duas variáveis:
    - prop.website.navegador; Que recebe o navegador escolhido, vem por default 'CHROME'
    - prop.website.url; Que recebe a URL que vamos executar nossos testes.

## Colocando o driver no local correto
- No diretório 'src\test\resources' coloque o Driver que fez o download anteriormente

## Importando projeto Eclipse IDE
- Com o Eclipse IDE aberto faça os passos abaixo:
    - Clique em 'File -> Import...';
    - Digite Maven na caixa de pesquisa;
    - Selecione 'Existing Maven Projecs';
    - Clique em 'Next';
    - Clique em 'Browse' e seleciona a pasta raiz que possui o arquivo pom.xml;
    - Clique em 'Next';
    - Clique em 'Finish';

## Estrutura e execução da ferramenta:
- Os passos abaixo explicam como podemos gerar relatórios allure, pdf, usar o excel como fonte de dados para o nosso teste e como é a estrutura da ferramente
- Estrutura e organização:
    - O projeto base é feito para que cada cenário de teste tenha seu Setup de after e before especifico, ou seja, o CT0001 tem o seu unico before e after para que caso necessario é possivel especificar condicoes exclusivas de before e after do cenário
    - Para rodar os testes executamos o package runner.
    - Podemos rodar um cenário rodando a classe runner desejada
    - O projeto esta desenvolvido em page object
- Excel:
    - A massa de dados pode ser coletada via Excel, é necessário instanciar a classe Excel e passar o caminho do arquivo xlsx dentro do arquivo start-properties
    - Todos os cenarios no arquivo .feature devem iniciar com CTXXXX onde substituimos XXXX por 4 numeros, isso servira como base para o software saber de qual linha ele olhara a massa de dados.
    - Ao pedir dados com a classe Excel, chamamos o método getData e passamos o nome da Coluna de onde queremos o dado, e o software sabendo que estamos no cenario CT0001 (por exemplo) faz a busca nome coluna x prefixo nome cenario e traz a informação da célula de intersecção deles. Por isso é necessário que a primeira coluna da tabela cotenha o mesmo prefixo existente no nome do cenário.
- Report:
    - Após a execução de todos os cenarios de teste executar o comando "mvn allure:serve" para gerar um relatorio em html com status de todos os cenarios, prints e ect.
    - Um pdf é gerado após a execução do cenário de teste na pasta target\reports
