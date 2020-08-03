# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Location References
Below are the location details 

* Swagger - 
	path - src/main/java/com.api.conference.swagger/SwaggerConfig
	url  - http://localhost:8080/swagger-ui.html 
* log - src/main/resources/logback.xml
* H2 DataBase URL: http://localhost:8080/testdb
* data.sql - Defined queries to have an immediate data on H2 DB startup 

Note: I did not create an API seperately to register as a session presenter. I have designed in such a way that we can make use of the first or second API to register a presenter 