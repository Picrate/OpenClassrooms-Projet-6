[![forthebadge](https://forthebadge.com/images/badges/made-with-typescript.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.png)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/build-with-spring.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/code-it-test-it-break-it.svg)](https://forthebadge.com)

# MDD Plateform MVP version
Yoga App is a Web Application for managing yoga sessions.

## 🔥Features

- Register as new user & secured Login / Logout
- Create a new Post for specific topic
- Post Comment on existing posts
- Subscribe / Unsubscribe to topics
- Manage user profile

# Requirements
## Frontend
- [NodeJS](https://nodejs.org/en)
## Backend
- [Java jdk](https://www.oracle.com/en/java/technologies/downloads/) >= v.8
- [MongoDB Community Edition Document Database](https://www.mongodb.com/products/self-managed/community-edition)

# Technologies

## Frontend

- NodeJS >= 10.13
- Angular 14
- RxJs v.7.5.6

## Backend

- Java jdk >= v.8
- Maven
- Spring boot v. 2.7.3
- Spring Security
- Spring Data JPA
- Spring validation
- JsonWebToken v. 0.12.5
- Lombok
- MapStruct v. 1.5.5.Final
- MapStruct Spring extension v. 1.1.1
- Jaxb-api
- MongoDB Community Server 6.0

# Authors

- Coder : [Patrice](https://github.com/Picrate)

# 🧬 Installing and running

## Running locally for development

To install locally, you must first clone the repository.
```shell
git clone https://github.com/Picrate/OpenClassrooms-Projet-6.git
```

## Install MySQL server

If MySQL server is not installed in your computer follow these installation instructions:

- [Download & Install](https://www.mongodb.com/try/download/community)
- [MongoDB Reference Manual](https://www.mongodb.com/docs/manual/)
- [MongoDB IDE](https://studio3t.com/fr/)

### Create Database, MongoDB user & configure Grant Access
First we connect to MongoDB;
- From shell at the root of the project
```shell
$ mongosh
mongodb > 
```
- Then create a user with password (choose you own)
```shell
mongodb> db.createUser({user: "<name>", pwd: "<cleartext password>", roles: [{ role: "readWrite", db: "mdd" }],})
{ ok: 1 }
mongodb > exit
```
- Import initial datas (mongoDB/roles.json) in mdd database
```shell
$ mongoimport --db=mdd --collection=roles --file=roles.json
```

Your database is ready.

## Backend
Backend application can be found in "back" subdirectory.

### Modify your backend application.properties
- Modify src/main/resources/application.properties to suite your database name and dbUsername & password
```
spring.mvc.pathmatch.matching-strategy= ANT_PATH_MATCHER

spring.data.mongodb.host=${DB_HOST}
spring.data.mongodb.port=${DB_PORT}
spring.data.mongodb.database=${DB_NAME}
spring.data.mongodb.username=${DB_USER}
spring.data.mongodb.password=${DB_PASSWORD}


# JWT
oc.app.jwtSecret=${JWT_SECRET}
oc.app.jwtCookieName= mdd-jwt
# Expiration 1H
oc.app.jwtExpirationMs=3600000

# Refresh Token
# expiration Time: 24H
oc.app.jwtRefreshExpirationMs=86400000
oc.app.jwtRefreshCookieName=mdd-jwt-refresh
```
### Create .jar package
```bash
mvn clean install
```
This will clean the project, download and install required dependencies, run tests and compile project into .jar file.

N.B. Tests are disabled in pom.xml

### Running application
Compiled application can be found in target directory
To run this application, copy the 'mdd-api-1.0.0.jar' to a clean directory then run:
```bash
java -jar mdd-api-1.0.0.jar
```
This will run springboot executable and the backend API server will be accessible to 'http://localhost:8080'

**N.B.** Be sure to have your MongoDB server instance up and running before launching application.

## Frontend
The frontend project is located at 'front' directory.
### Installation
To build and run this project, NodeJS is required on you computer.
- In a terminal located in the front directory:
```bash
npm install
```
It will download all required dependencies.
### Running in developpement mode
In a Terminal under the front directory:
```bash
ng run
```
It will launch frontend project in http://localhost:4200
### To build production files
Run:
```bash
ng build
```
The build artifacts will be stored in the `build/libs` directory.

### externalize variables for production use
You can externalize application.properties file outside the jar.
- Copy application.properties in a directory `config` at the root directory containing the jar.
- Replace variables names used for development with the production values.

Launch jar with:
```shell
java -jar mdd-api-1.0.0.jar
```