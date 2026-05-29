# ESTATE API

## Installation

1. clone the project
1. create a database
1. [create a user](https://dev.mysql.com/doc/refman/5.7/en/create-user.html#create-user-overview)
1. create schema with `docs/conception/script.sql`
    - you can feed your db with `docs/conception/feed.sql`
1. copy application.properties.example to application.properties and set your DB credentials and other informations for your installation
1. build and run your app


## DB creation

```sql
-- database creation
create database estate_db;
-- user creation
create user 'estate_user'@'localhost' identified by 'estate_pwd';
-- grant admin access to user
grant all privileges on estate_db.* to 'estate_user'@'localhost';

-- exit mysql prompt
exit
``` 


```bash
# test your connection, type password when prompted
# you should be connected to the database
mysql -u estate_user -p -D estate_db
# exit mysql again and apply the script

# create db schema
mysql -u estate_user -p -D estate_db < ./docs/conception/script.sql
# optionally you can insert dummy data
mysql -u estate_user -p -D estate_db < ./docs/conception/feed.sql
```

## App configuration

With the given example, your application.properties file should look like

```conf
# 3306 is the default port for mysql servers
spring.datasource.url=jdbc:mysql://localhost:3306/estate_db?serverTimezone=UTC
spring.datasource.username=estate_user
spring.datasource.password=estate_pwd
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

## Build source

Your IDE should provide an easy way to build and run your project.

## Test api

You can test the API using swagger

`http://localhost:<server.port>/<springdoc.swagger-ui.path>`
with the default values it should be available here : [http://localhost:3001/swagger-ui.html](http://localhost:3001/swagger-ui.html)

## Stack

- java 25
- gradle 4.0.29
- mariadb 10.11.14 (mysql 15.1)

