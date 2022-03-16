package com.graham;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collection;
import java.sql.Types;

/**
 * An implementation of BookService interface that fetches data from a DB */
public class BookServiceDBImplementation implements BookService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BookMapper());
    }

    @Override
    public Book getBook(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id LIKE ?", new BookMapper(), id);
    }

    @Override
    public int addBook(Book book) {
        return jdbcTemplate.update(
                "INSERT INTO books (isbn, name, author) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getName(),
                book.getAuthor()
        );
    }
}
