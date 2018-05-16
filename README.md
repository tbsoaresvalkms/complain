## Requisitos
* Java 8+
* Lombok (necessário plugin em IDEs)
* Maven (somente para o build)
* Docker (opcional)
* MongoDB
* port 8080

## Executando o projeto
`git clone https://github.com/tbsoaresvalkms/complain.git`

`cd complain`

`docker-compose up`

## Executando os tests
#### Necessario mongoDB
`git clone https://github.com/tbsoaresvalkms/complain.git`

`cd complain`

`mvn clean`

`mvn test`

## Componentes da Aplicação
 
* Spring Boot
* Spring Rest
* Hystrix (Circuit Break e metricas)
* Sleuth (Log Track)
* Swagger
* Mockito
* AssertJ
* Lombok
* Maven

## Comentários

* Swagger - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

* Hystrix-Dashboard - [http://localhost:8080/hystrix](http://localhost:8080/hystrix)

* Hystrix-Stream - [http://localhost:8080/hystrix.stream](http://localhost:8080/hystrix.stream)

* Foi criado uma extensão da interface MongoRepository para aceita queries dinâmicas, permitindo consultas com uma ou mais condições. 
a classe ComplainController_IndexTests aborda essas consultas.