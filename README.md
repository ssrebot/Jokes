# Jokes

Jokes is a simple Spring Boot application that is used for displaying jokes. Additional features include liking, disliking jokes as well as adding new jokes. Adding jokes is enabled only for authorized users.


## Technologies

Project uses several different technologies:
  - Java
  - Spring Boot
  - Postgres
  - Flyway
  - Thymeleaf

## Prerequisites

To successfully run application there are certain conditions your environment will have to satisfy:

  - There has to be database server with PostgreSql database named "jokes" running on http://localhost:5432 with username/password set to: postgres/postgres. To modify these settings "application.properties" fields:
     * spring.datasource.url
     * spring.datasource.username
     * spring.datasource.password 
 need to be modified.
  - JRE 8 installed
  
## Run application

Application's .jar file is available for download on this link: https://ufile.io/o3mti. After downloading .jar file, following cmd command has to bi run while positioned in folder where .jar file was downloaded:
  - java -jar jokes-0.0.1-SNAPSHOT.jar
  
## Usage

To access apllication after successfully running it URL-s must be typed in your favorite browser:
  - http://localhost:8080/ - gives user a list of jokes with their categories and number of likes/dislikes. User can navigate through jokes using page navigation on the bottom of the page. Also, jokes can be liked/disliked by user by clicking like/dislike button next to each joke.
  - http://localhost:8080/new - enables user to enter a new joke. To use this feature, user needs to be signed in. Couple different username/password options are preset:
    * pperic/pero
    * iivic/ivan

## Built With

  - Maven

### Authors:

 - ssrebot
 
### Acknowledgments

  - https://spring.io/guides
  - https://www.jetbrains.com/help/idea/spring-boot.html
  - http://zetcode.com/springboot/postgresql/
  - https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/
