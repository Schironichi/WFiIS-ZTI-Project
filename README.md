# WFiIS-ZTI-Project
Library management program for employees - Vue.js frontend / Spring + Spring Boot + PostgreSQL backend.

The front and backend project structure as well as backend javadoc documentation were created using IntelliJ IDEA IDE. There also is a configuration file that allows to build this project using Docker-compose file and prepared build sequence.

The project was created using:
- Docker,
- Docker-compose,
- backend:
  - OpenJDK Java 17,
  - PostgreSQL JDBC,
  - Spring / Spring Boot,
- frontend:
  - Vue.js,
  - npm.

## Documentation

To build the application, add PostgreSQL database connection parameters [here](src/ZTI_Proj/src/main/resources/application.properties), open src/ZTI_Proj folder in IntelliJ IDEA and run MakeDocker configuration.

Documentation in Polish generated with the javadoc command for the backend is available [here](doc/).

All database generation files are located in the [SQL](SQL) directory. There is also an [Entity Relationship Diagram](ERD.pdf) the represents the structure of the database.

## Genesis

The project was created for the subject Advanced Internet Techniques (Zaawansowane technologie internetowe) during the 1st semester of my Second-cycle (engineer) studies.

## Showcase

The project presentation in the form of screenshots is available [here](Screenshots/) and in the above documentation.
