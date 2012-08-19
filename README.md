## Heroku
Branch criado para testar a API BuscaPé no ambiente Heroku (<http://www.heroku.com>)

## Prerrequisitos
1. Java, Maven, Git e cliente Heroku (detalhes em <https://devcenter.heroku.com/articles/java>)

## Instalação
1. Faça um clone deste branch:
<pre>git clone https://github.com/brunolellis/com.buscape.java.git -b web

2. A partir do diretório com.buscape.java:
<pre>cd com.buscape.java

3. Compilar com o maven:
<pre>mvn package

4. Iniciar o jetty:
<pre>java -cp target/classes:"target/dependency/*" br.com.brunolellis.cliente.web.BuscapeWeb

5. Acesse <http://localhost:5000>


## Deploy no ambiente Heroku
Com a aplicação funcionando, vamos fazer o deploy no ambiente Heroku utilizando a sua conta previamente criada.

1. Criar a aplicação no Heroku com o seguinte comando:
<pre>heroku create</pre>

2. Deploy na nova aplicação com:
<pre>git push heroku web:master</pre>

3. Abrir a aplicação:
<pre>heroku open</pre>

### Links úteis
1. Artigos Java no Heroku: <https://devcenter.heroku.com/categories/java>
2. Exemplo de aplicação com Java Servlet + Heroku: <https://devcenter.heroku.com/articles/java>
3. Exemplo de aplicação com Spring Roo + Heroku: <https://devcenter.heroku.com/articles/spring-mvc-hibernate>
4. Problemas com chave SSH: <https://devcenter.heroku.com/articles/keys#adding_keys_to_heroku>