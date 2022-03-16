<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Book</title>
    </head>
    <body>
        <c:url var="add_book_url" value="/books/add"/>
        <div>
            <h3>ISBN</h3>
            <p>${book.getIsbn()}</p>

            <h3>Name</h3>
            <p>${book.getName()}</p>

            <h3>Author</h3>
            <p>${book.getAuthor()}</p>
        </div>
    </body>
</html>
