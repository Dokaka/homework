# homework
GUIDELINE: HOW TO USE SIMPLE API FOR USER

Step 1: Open IDE
-	IntelliJ
-	MySQL WorkBench
-	Postman
Step 2: Create database on MySQL Workbench

create database homework;
use homework;
create table users(
id int not null auto_increment ,
username varchar(60) not null unique,
email varchar(75),
address varchar(60),
primary key (id)
);

Step 3: Configure database connection in “src/main/resources/application.properties”

server.port=8085
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD
spring.datasource.url=jdbc:mysql://localhost:3306/homework
spring.jpa.hibernate.ddl-auto=update


Step 4: Add dependencies
Add dependencies in file pom.xml
<dependencies>
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
   </dependency>

   <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
   </dependency>
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
      <exclusions>
         <exclusion>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
         </exclusion>
      </exclusions>
   </dependency>

   <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
      <version>2.2.2.RELEASE</version>
   </dependency>
   <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <scope>runtime</scope>
   </dependency>

   <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
   </dependency>

</dependencies>


Step 5: Run Application

Step 6: Services 
-	GET /users
Function: get all users
-	GET/users/username/{username}
Function: get user by username
-	GET/users/email/{email}
Function: get user by email
-	POST/users
Body:
{
“username” :  String,
“email” : String,
“address” : String
}
Function: Create users


-	PUT /users/{username}
Body:
{
“username” :  String,
“email” : String,
“address” : String
}
Function:  update users by username

-	DELETE /users/{username}
Function: Delete user by username


