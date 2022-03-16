## Spring boot/Java Servlet tutorial
A quick overview of the important files here:

```
- src
    - main
        - java
            - com.graham
               | App.java - the entrypoint/way to start the web server.
               | Book.java - the class which represents the books we are selling.
               | BookController.java - the class which contains all web related logic for interfacing with the web server via HTTP JSP pages.
               | BookService.java - An interface which defines what methods are available from the Database access layer e.g fetch 1, fetch a list, add a new book, etc.
               | BookServiceDBImplementation.java - An implementation of BookService.java that uses SQLite via the SpringData library to access data stored in a SQLite DB.
               | BookMapper.java - A utility class which allows us to map a book record in the DB to a Book.java class.
```

We also have an important directory with some files `webapp/WEB-INF`.
This directory stores the JSP files we use to render content on the user's web browser (Chrome, Firefox, etc.).

There is also a `resource` folder which contains some SQL code to create a new DB and insert some test records on server start.

## How to run (I can help with this):
1. Install Maven onto your computer and make sure it is accessible from your command line.
2. Navigate to this directory on your command line and run mvn spring-boot:run