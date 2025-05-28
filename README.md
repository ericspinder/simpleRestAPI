# Simple Rest API

This is a Spring Boot application using Hibernate JPA, an embedded Tomcat server, and an in memory H2 database.

To run on Windows:

    .\mvnw.cmd clean test spring-boot:run

   once that's started run JMeter...

    .\mvnw.cmd jmeter:jmeter
To run on Unix:

    ./mvnw clean test spring-boot:run

   Once that's started run the JMeter maven goal

    ./mvnw jmeter:jmeter


"/hello/{name}" will echo the name back to you, while saving it to the database.

http://localhost:8081/hello/Handsome

http://localhost:8081/hello/Pal

http://localhost:8081/hello/Buddy

"/log" will repeat back a list of all names and seen counts it has seen since the application was last started.

http://localhost:8081/log

"/person/{name}/count" will show the number of times the named person has been seen.

http://localhost:8081/person/Handsome/count

"/interest/{person}/{interest}" will add an interest. The action parameter allowed is either add (default) or remove.

http://localhost:8081/interest/Handsome/people

http://localhost:8081/interest/Handsome/people?action=remove

http://localhost:8081/interest/Handsome/cats

http://localhost:8081/interest/Pal/cats

http://localhost:8081/interest/Pal/people

http://localhost:8081/interest/Buddy/people

"/interest/{person}/list" will list every interest the named person has, if any.

http://localhost:8081/interest/Handsome/list

"/person/{name}/friend/{friendName}" will add a friend, or remove one.

http://localhost:8081/person/Handsome/friend/Pal

http://localhost:8081/person/Handsome/friend/Buddy

http://localhost:8081/person/Handsome/friend/MyMan (checking /log, MyMan should at this point have been seen 0 times)

http://localhost:8081/person/Handsome/friend/MyMan?action=remove

"/person/{name}/friends" will list all your friends

http://localhost:8081/person/Handsome/friends

http://localhost:8081/person/Buddy/friends

"/person/{name}/friends/shared-interests" will list those who have the same interests

http://localhost:8081/person/Handsome/friends/shared-interests

To end your tomcat session simply close the command window, or kill the process in your IDE. As it's an in memory database all values will be lost.