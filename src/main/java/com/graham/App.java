package com.graham;

/**
 * This file is the main class for the server. This file contains a main method that initiates a Spring web application server running on an
 * internal Tomcat server (supplied by Spring via a Maven package). The app is a Simple book store that allows a user to see all books available and to add a new book.
 *
 * The App class inherits a class called 'SpringBootServletInitializer' and this is how Java knows how to run the server. Some more information at: https://zetcode.com/springboot/springbootservletinitializer/
 *
 * We also declare some Java beans here which can be automatically injected into our code using the dependency injection/Inversion of Control principle/pattern. Not required to know how it works, but on a high level
 * Spring/JavaEE framework will automatically inject each Java Bean into any class that makes a reference to it. This concept is extremely useful when accessing a database or, you want to expose some service level class/functionality
 * to the rest of your codebase. See more info at: https://www.baeldung.com/spring-bean
 *
 * In this case we declare two beans. One for the connection to our Database, which is SQLite: an in memory database which stores relational data in a file on your machine. This makes it really simple to prototype your project before moving
 * to a hosted database server. We also declare a bean for access to our BookService class. This is how we will interface with the database.
 *
 * A quick overview of the important files here:
 *
 *  App.java - the entrypoint/way to start the web server.
 *  Book.java - the class which represents the books we are selling.
 *  BookController.java - the class which contains all web related logic for interfacing with the web server via HTTP JSP pages.
 *  BookService.java - An interface which defines what methods are available from the Database access layer e.g fetch 1, fetch a list, add a new book, etc.
 *  BookServiceDBImplementation.java - An implementation of BookService.java that uses SQLite via the SpringData library to access data stored in a SQLite DB.
 *  BookMapper.java - A utility class which allows us to map a book record in the DB to a Book.java class.
 *
 *  We also have an important directory with some files webapp/WEB-INF.
 *  This directory stores the JSP files we use to render content on the user's web browser (Chrome, Firefox, etc.).
 *
 *  There is also a resource folder which contains some SQL code to create a new DB and insert some test records on server start.
 *
 *  How to run (I can help with this):
 *  1. Install Maven onto your computer and make sure it is accessible from your command line.
 *  2. Navigate to this directory on your command line and run mvn spring-boot:run
 *
 * */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "com.graham")
public class App extends SpringBootServletInitializer {

    @Bean
    public BookService bookService() {
        return new BookServiceDBImplementation();
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:db.sqlite:myDb?cache=shared");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
