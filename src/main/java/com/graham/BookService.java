package com.graham;

import java.util.Collection;

public interface BookService {
    Collection<Book> getBooks();
    Book getBook(int id);
    int addBook(Book book);
}
