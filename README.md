## Heroku
Branch criado para testar a API BuscaPé no ambiente Heroku (<http://www.heroku.com>)

## Prerrequisitos
1. Java, Maven, Git e cliente Heroku (detalhes em <https://devcenter.heroku.com/articles/java>)

## Instalação
1. Faça um clone deste branch:
<code>git clone https://github.com/brunolellis/com.buscape.java.git -b web</code>

2. A partir do diretório com.buscape.java:
<code>cd com.buscape.java</code>

3. Compilar com o maven:
<code>mvn package</code>

4. Iniciar o jetty:
<code>java -cp target/classes:"target/dependency/*" br.com.brunolellis.cliente.web.BuscapeWeb</code>

5. Acesse <http://localhost:5000>


## Deploy no ambiente Heroku
Com a aplicação funcionando, vamos fazer o deploy no ambiente Heroku utilizando a sua conta previamente criada.

1. Criar a aplicação no Heroku com o seguinte comando:
<code>heroku create</code>

2. Deploy na nova aplicação com:
<code>git push heroku web:master</code>

3. Abrir a aplicação:
<code>heroku open</code>

### Links úteis
1. <https://devcenter.heroku.com/categories/java>
2. <https://devcenter.heroku.com/articles/java>
3. <https://devcenter.heroku.com/articles/spring-mvc-hibernate>