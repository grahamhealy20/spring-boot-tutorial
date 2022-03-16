<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>View Books</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:if test="${addBookSuccess}">
            <div>Successfully added Book with ISBN: ${savedBook.getIsbn()}!</div>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Name</th>
                    <th>Author</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td>${book.getIsbn()}</td>
                        <td>${book.getName()}</td>
                        <td>${book.getAuthor()}</td>
                        <td><a href="/books/${book.getId()}">View book</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/books/add">Add a book</a>
    </body>
</html>