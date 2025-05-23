# Simple Rest API

This is a Spring Boot application using Hibernate JPA, an embedded Tomcat server, and an in memory H2 database.

To run on Windows:
 
    ./mvnw.cmd spring-boot:run
To run on Unix:
    
    .\mvnw spring-boot:run
    


The "/hello/{name}" will echo the name back to you, while saving it to the database.
http://localhost:8081/hello/Handsome

The /log will repeat back a list of all names it has seen since the application was last started.
http://localhost:8081/log

To end your tomcat session simply close the command window, or kill the process in your IDE. As it's an in memory database all values will be lost.