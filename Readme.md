# Negotium

Negotium is a web-service for job searching.


### Roles:
- Guest 
    - Can browse list of vacancies and look for them by position name, country and specialization.
    - Can log in or register anytime. 
- Employee 
    - Can create and update the resume.
    - Can browse list of vacancies and look for them by position name, country and specialization.
    - Can respond on vacancy and delete vacancies, that employee was responded on.
    - Can browse employees.
    - Can delete account.
- Employer 
  - Can fill info about company.
  - Can open vacancy.
  - Can delete vacancy.
  - Can see employee's resume.
  - Can delete account.
  - Can browse list of vacancies and look for them by position name, country and specialization.

___
## Requirements

To run this project you will need:
- Java 13 or higher
- PostgreSQL database
- Maven - for building sources
- Tomcat - for starting server

Other dependencies will be downloaded by maven.
___
## SQL 

First you need to install and start PostgreSQL server. Then you should run sql scripts
from /sql folder:
- drop-db.sql - will drop database job_search if it exists
- create-db.sql - will create the database job_search and its user (job_user)
- create-table.sql - will create tables and enums in database job_search
- fill-table.sql - will fill job_search tables with some content, such as country catalog,
  specializations, etc.
- permission-user.sql - will give permission to user

___

## Web 

Application context is /main
___
## Testing

Dao tests require test database. So, if you need to run project tests, create database
job_search_test by scripts from /sql/test-db folder.


___
## Usage
After running webapp in Tomcat container, you can start using application. 
To start, you can register or get access to functional of non-authorized users.


___
## Database structure

![](https://github.com/Pokemon3108/job-search/blob/master/readme/db-structure.png "Job_search_db")
___
## Technologies

There is list of technologies used in project:
- Java 13
- Maven - Project build tool
- Java EE
- Tomcat - Servlet container
- PostgreSQL - OpenSource SQL database
- JDBC - Java Database Connectivity API (tool for communication Java application with databases)
- JSTL - Library for JSP
- TestNG- Test framework
- Mockito - Mocks framework, useful for testing
- Lombock  is a java library that automatically plugs into editor and build tools, spicing up java code
- Log4j2 - Logging library