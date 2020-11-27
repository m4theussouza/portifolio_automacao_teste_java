
# Projeto Teste Login SwagLab

Desafio para fazer login com alguns usuarios do SwagLab.

## Preparação do ambiente

- Download e Instalação
    - Baixe e instale o Java [JDK Java Download](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html); [Java Instalação](medium.com/@mauriciogeneroso/configurando-java-4-como-configurar-as-variáveis-java-home-path-e-classpath-no-windows-46040950638f).
    - Baixe e instale o [Eclipse](https://www.eclipse.org/downloads.) IDE.
    - Baixa o driver do Navegador que deseja executar o teste. [Chrome](https://sites.google.com/a/chromium.org/chromedriver/downloads); [Firefox](github.com/mozilla/geckodriver/releases); [IE](selenium-release.storage.googleapis.com/index.html);
    - Baixe e instale o Maven. [Maven Download](https://maven.apache.org/download.cgi); [Maven Instalação](https://dicasdejava.com.br/como-instalar-o-maven-no-windows)

	

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

## Podemos executar os testes de duas formas:
- A primeira maneira é diretamente pelo eclipse e gera somente um arquivo .pdf com todos os prints que tiramos no decorrer do cenário:
    - Execute com JUnit a classe Runner.java
    - Os testes serão executados;
- A segunda maneira gera um arquivo .pdf com todos os prints e gera um html com as features executadas, exibindo tempo, porcetagem de cenários com status passado:
    - Abra o cmd ou power shell;
    - Execute o comando 'mvn test verify' sem aspas;
    - Os testes serão executados;
- Os arquivos .pdf estarão na pasta 'target\report';
- Os arquivos .html estarão na pasta 'target\cucumber-html-reports';
