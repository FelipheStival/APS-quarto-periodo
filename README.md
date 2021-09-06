# Aplicativo para análise de sentimentos
Aplicativo para análise de performance de algoritmos de ordenação.

# Sumário

<!--ts-->
   1. [Sumário](#Sumário)
   2. [Pré-requisitos](#Pré-requisitos)
   3. [Instalação](#Instalação)
   4. [Execução e objetivo](#Execução)
<!--te-->

# Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina as seguintes ferramentas: <br>

<!--ts-->
   1. [Java 11](#1-Java)
   2. [Eclipse IDE](#2-Eclipse)
   3. [MySQL e MySQL Workbench](#MySQL)
<!--te-->

### 1. Java
Para obter o Java, você deve acessar o seguinte endereço: https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html e realizar o download e a instalação do Java.

Depois de baixar e instalar o Java, é necessário configurar as variáveis de ambiente, para configurar as variáveis de ambiente realize os seguintes do tutorial abaixo.<br>
https://confluence.atlassian.com/confbr1/configurando-a-variavel-java_home-no-windows-933709538.html


### 2. Eclipse
Depois de ter instalado o Java é necessário baixar a IDE eclipse para a execução do código, a IDE pode ser obtida pelo seguinte link:

https://www.eclipse.org/downloads/

### 3. MySQL
Depois de instalar o Java e Eclipse, é necessário instalar o MySQL. 

https://dev.mysql.com/downloads/workbench/


# Instalação

Para realizar a instalado você precisa realizar os seguintes passos.

<!--ts-->
   1. [Obter chave de acesso Twitter](#1-Chave-acesso)
   2. [Configurar chave da API no aplicativo](#2-Configurar-API-no-codigo)
<!--te-->

### 1. Chave-acesso

Para obter a chave de acesso para a API do facebook, você deve acessar o endereço: https://developer.twitter.com/en/docs/twitter-api, depois de realizar o cadastro você deve solicitar ao Twitter acesso a API e esperar o acesso ser liberado.

### 2. Configurar API no codigo

Depois de obter a chave de acesso para a API do Twitter, você precisa acessar o arquivo Modules/globals.R e configurar as variáveis globais. Você encontrara o arquivo nesse formato e deve editar as variáveis de acordo com suas informaçoes de acesso.

```r
#=================Configuracao interface============================
NOME_APLICACAO = "APS-2020"
#===================================================================


#=================Configuracao API twitter==========================
api_key             = ""
api_secret          = ""
access_token        = ""
access_token_secret = ""
#===================================================================
```

# Execução

Depois de ter instalado basta executar o programa pelo RStudio. O programa possui uma dashboard web onde o usuário pode digitar o assunto e o programa irá pesquisar os Twitter atráves da API e retornar uma análise de sentimento que pode ser análisada por meios de gráficos interativos.

