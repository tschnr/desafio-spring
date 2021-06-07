# Desafio-Spring
Desafio proposto pela digital house e mercado livre.

## Executando o Projeto

Após ter baixado o projeto deste repositório, basta ir até a pasta raiz e executar o comando abaixo:

```
> mvn spring-boot:run
```

## Banco de dados H2

URL:
```
http://localhost:8080/h2/
```

- Driver Class: org.h2.Driver
- JBDC URL: jdbc:h2:mem:socialmelidb
- User name: sa
- Password: System2021@

No banco já existem regristros inseridos de usuarios, conforme abaixo:

| ID  | Name      | Seller
| --- | --------- | ------------
| 1   | user1     | FALSE
| 2   | user2     | FALSE
| 3   | user3     | FALSE
| 4   | seller1   | TRUE
| 5   | seller2   | TRUE
| 6   | seller3   | TRUE

## Testes utilizando o postman

Foi exportado a coleção para teste do aplicativo. Sendo assim,
precisando somente importar a coleção no postman para realizar os testes. O nome do arquivo é:
_socialMeli.postman_collection.json_

