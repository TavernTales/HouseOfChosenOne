
# HOUSE OF CHOSEN ONE

## Configuração
Primeiro passo é criar um servidor Spigot/Purpur/Paper da Versão [1.19.X](https://getbukkit.org/get/f92ff75a893a50ec39da5cb90c29c657), pode utilizar este [Guia](https://www.spigotmc.org/wiki/spigot-installation/) para te ajudar a configurar o ambiente.

Após a criação do servidor será necessário clonar o projeto e importar em sua IDE, recomendo a utilização do [IntelliJ Community](https://www.jetbrains.com/idea/download) mas poderá utiliza a de sua preferência. O Plugin funciona com JAVA Versão 17 e Gradle com Groovy.


Para conseguir Exportar para o Diretório ideal do seu servidor, você precisará adicionar um Profile novo com seu Nome.

EX:

![img](https://i.imgur.com/j5ALJwG.png)


E nas Configurações dos seus Executaveis, será necessário informar qual task quer executar, no campo **Run** escreva *shadowJar* e na sequencia crie uma **Environment variables** com seu perfil respectivo como exemplo abaixo:

EX:

![img2](https://i.imgur.com/TsDQT1D.png)


### Mongo DB

As conexões de banco de dados são feitas com mongodb, utilizando uma conta do Atlas em núvem ou uma conexão local.

A primeira conexão é feita com a URL local: *mongodb://localhost:27017*
mas poderá editar essa url mudando no arquivo **config.yml** na pasta do plugin.

## Proposta
É desenvolver um plugin que permita os jogadores se assossiar a uma facção definida como Casas, e apoiar o desenvolvimento dessa casa realizando atividades em seu nome, sendo missões, dungeons e até mesmo confrontando os inimigos de sua facção. 
