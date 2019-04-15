# Books JDBC assignment

This project is a simple "book manager" application. Your task is to add SQL support using [JDBC](https://en.wikipedia.org/wiki/Java_Database_Connectivity).

Later, you will learn how to use an [object-relational mapping](https://en.wikipedia.org/wiki/Object-relational_mapping) library such as Hibernate, but in this exercise, you can learn how to do the same manually.

The project is using the [DAO pattern](https://www.baeldung.com/java-dao-pattern) (Data Access Object): there are domain classes (`Author`, `Book`) that do not know anything about database access, and DAO classes (`UserDao`, `BookDao`) responsible for loading and updating the data.

## 1. Open and run the project

Open the project in IntelliJ. Keep in mind that this is a Maven project, so you need to open the `pom.xml` file, not the whole directory.

Run the Main class. Look at the available features. The SQL database, and "Books" part, are not implemented yet.

## 2. Create a database and connect to it

Install PostgreSQL and set up a user for it. See [PostgreSQL Initialization and Configuration](https://codecool.gitlab.io/codecool-curriculum/web-python/#/../pages/tools/postgresql-installation-and-configuration) in the Web module.

Create a `books` database:

```sh
createdb books
``` 

Load the schema and data from the `books.sql` file in this repository.

```sh
psql books < books.sql
```

Then check if the data is there:

```
$ psql books

books=# select * from author;
 id |  first_name  | last_name | birth_date 
----+--------------+-----------+------------
  1 | J.R.R.       | Tolkien   | 1982-01-03
  2 | Douglas      | Adams     | 1952-03-11
  3 | George R. R. | Martin    | 1948-09-20
  4 | Frank        | Herbert   | 1920-10-08
(4 rows)
```

Make sure the program can connect to the Postgres database (run `Main` class and select SQL database). You might need to modify connection parameters.

## 3. Implement SQL DAO

Implement the `AuthorDaoSql` class, so that the methods run SQL queries. Make sure the program works with the SQL database 

The [JDBC tutorial](https://www.tutorialspoint.com/jdbc/) should help.

## 4. Add Books

Implement the second type of object (`Book`). A Book should contain an autor and title:
```java
public Book(Author author, String title ) {
    this.author = author;
    this.title = title;
}
``` 

There is some skeleton code for Books (e.g. initial data) written and commented out, you can use it.

You will need to create appropriate classes. Start with an in-memory DAO, then add SQL support. You will need to add an appropriate table to `books.sql`, then reload it in Postgres.

## Further reading

* [Object-relational mapping](https://en.wikipedia.org/wiki/Object-relational_mapping) 
* [Java DAO pattern](https://www.baeldung.com/java-dao-pattern)
* [JDBC tutorial](https://www.tutorialspoint.com/jdbc/)