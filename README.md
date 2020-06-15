# cnj-persistence-sql

Showcase of a simple cloud native Java application using JPA-based persistence to persist its domain model in
a PostgreSQL database.

The actual integration of JPA-based persistence is demonstrated with three different Java backend technologies:

* Java EE (see: [cnj-persistence-sql-backend-javaee](cnj-persistence-sql-backend-javaee/README.md))
* Eclipse MicroProfile (see: [cnj-persistence-sql-backend-micro](cnj-persistence-sql-backend-micro/README.md))
* Spring Boot + Spring Data (see: [cnj-persistence-sql-backend-spring](cnj-persistence-sql-backend-spring/README.md))
* Quarkus (see: [cnj-persistence-sql-backend-quarkus](cnj-persistence-sql-backend-quarkus/README.md))

All showcases use a common resources container project, which deploys all attached resources to Kubernetes (see: [cnj-persistence-sql-resources](cnj-persistence-sql-resources/README.md)])

In this showcase, PostgreSQL is used as the common RDBMS. 

## Status
![Build status](https://drone.at.automotive.msg.team/api/badges/cloudtrain/cnj-persistence-sql/status.svg)

## Build this showcase 

### Prerequisites

In order to run the build, you have to install the following tools locally:
* Docker
* Docker Compose 
* Maven
* Java JDK 11   

### Run Maven

You can build all showcase applications by running Maven:
```
mvn clean install -P pre-commit-stage
```

The Maven build will create Docker images for all showcase applications and run system tests on them.