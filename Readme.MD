To run and test microservice - first off all you needs to create database structure.

*************************************************

1. 
in directory \sql
you can find sql scripts, which helps you to create database and tables.

first file to run SQL Script: create_bd_tables.sql
it will create structure.

Second file: Data.sql
it will fill some data to the tables.

how to run import to Postgres DB
psql \
  -h localhost \
  -p 5432 \
  -U user \
  database-name \
  -f table.sql

user - is your user access to postgres
database-name - is will be "rules" here.

or you can run script from Postgres PGADMIN User Interface.


*************************************************


2. After that you can run microservices.

First you must run \production_jar\dbholder.jar

[path]\java -jar [path]\production_jar\dbholder.jar

Second 
[path]\java -jar [path]\production_jar\transaction.jar

You can find this microservice in URL
dbholder: (Rules from DB)
http://localhost:8081/getRules
http://localhost:8081/getSources
http://localhost:8081/getTypes

transaction: (main logic)
http://localhost:8082/


*************************************************

In Folder's \dbholder  and  \transactions  you can find source code.
