There are Java project template that integrates next technologies:
1. Spring Boot 2
2. Spring Data JPA
3. QueryDSL JPA
4. Liquibase
5. Postgres
6. MapStruct
7. Lombok
8. JUnit 5 + Integration Tests

## Build 
To build the project it is require to use next command

```bash
gradlew clean assemble
```

To build docker image it will require to use

```bash
docker build . -t app-template
```

## Run

To run the project it is require to use docker-compose

```bash
docker-compose -f ./docker/docker-compose.yaml up
```

To run only project dependencies it is require to evaluate 

```bash
docker-compose -f ./docker/docker-compose-deps.yaml up
```

## License
MIT