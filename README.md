## Spring QueryDsl Project Template

[![Build Status](https://travis-ci.org/Softeq/spring-querydsl-project-template.svg?branch=master)](https://travis-ci.org/Softeq/spring-querydsl-project-template)
[![Coverage Status](https://coveralls.io/repos/github/Softeq/spring-querydsl-project-template/badge.svg?branch=master)](https://coveralls.io/github/Softeq/spring-querydsl-project-template?branch=master)

There are Java project template that integrates next technologies:
1. [Spring Boot 2](https://spring.io/projects/spring-boot)
2. [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
3. [QueryDSL JPA](http://www.querydsl.com/)
4. [Liquibase](https://www.liquibase.org/)
5. Postgres
6. [MapStruct](https://mapstruct.org/)
7. [Lombok](https://projectlombok.org/)
8. [JUnit 5](https://junit.org/junit5/docs/current/user-guide/) + Integration Tests

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

## Architecture

Application provide next folder structure 
```
src
    /itest
    /main
    /test
```

Application provides next folders:
 - *itest* - this folder contains integration tests
 - *main* - this folder contains application code
 - *test* - this folder contains JUnit 5 tests

Main application contains folder structure that represent 
([hexagonal architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)))

```
com.softeq.app
    .domain
    .services
    .config
    .adapters
        .api
        .jpa
```

There are list of packages:
  - *domain* - this folder contains domain model of the application and repository interfaces
  - *services* - this folder contains services of the application
  - *config* - current folder contains application configurations
  - *adapters* - current folder contains external services adapters
  
Project contains several base classes:
1. *AbstractITest* - base integration test that configure context and rollback options for the tests
2. *AbstractRepositoryImpl* - base class for queryDsl based repositories

Gradle build support next options:
1. `-PdisableIntegrationTests` - this option allow to disable integration tests
2. `-PdisableJacoco` - this options allow to disable code coverage checks

**To build the code successfully it is required > 80% code coverage!**

## License
MIT
