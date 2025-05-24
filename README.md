
This is a Spring Boot application using Hibernate JPA, an embedded Tomcat server, and an in memory H2 database.

To run on Windows:

    .\mvnw.cmd spring-boot:run
To run on Unix:

    ./mvnw spring-boot:run



"/hello/{name}" will echo the name back to you, while saving it to the database.
http://localhost:8081/hello/Handsome

"/log"
The /log will repeat back a list of all names and seen counts it has seen since the application was last started.
http://localhost:8081/log

"/person/{name}/count" will show the number of times the named person has been seen.
http://localhost:8081/Handsome/count

"/interest/{person}/{interest}" will add an interest. The action parameter allowed is either add (default) or remove.
http://localhost:8081/interest/Handsome/people
http://localhost:8081/interest/Handsome/people?action=remove

"/interest/{person}/list" will list every interest the named person has, if any.
http://localhost:8081/interest/Handsome/list

To end your tomcat session simply close the command window, or kill the process in your IDE. As it's an in memory database all values will be lost.