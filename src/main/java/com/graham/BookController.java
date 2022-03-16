package com.graham;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * BookController is the main way that we interact with our server. The user goes to a specific HTTP url in their browser e.g.
 * http://localhost:8080/books/ and this will be mapped to a method inside this class. This is decided based upon the
 * `RequestMapping value below`. E.g.
 *
 *  GET /books/ -> viewBooks method
 *  GET /books/1 -> viewBook method
 *  GET /books/add -> addBookView method
 *  POST /books/add -> addBook method
 *
 * From there we can then do whatever Java code we like, which in this case is fetching data from the
 * database. Note that we use a Java feature called Java Bean's to instantiate an instance of the BookService class which then allows us to access
 * data inside our database in a controlled way and without having to know how it is implemented.
 *
 * We can then return data to the user via a `View`. A view is just a HTML page which can contain Java code. This is called Java Server Pages or JSP.
 * The view, which is also known as a 'Template' will be compiled and the result of the Java code will be included in the HTML which is returned to the user in their
 * web browser.
 *
 * Next steps: Try to add a new controller which allows the user to see Users which are stored in the database.
 * */

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // Use dependency injection to instantiate our DB connection, Spring/Java Servlet will provide the value `bookService`.
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // These `@` lines are called 'Annotations'
    @GetMapping("/")
    public String viewBooks(Model model) {
        // Fetch all books and present them to the user.
        /** Note:
         * `model` is the 'pipe' or 'connection' between our backend and our JSP pages. It's the only way that
         * we can get data into our JSP pages.
         * */
        model.addAttribute("books", bookService.getBooks());
        return "view-books";
    }

    @GetMapping("/{id}")
    public String bookDetail(@PathVariable int id, Model model) {
        // Fetch a book from the DB and return it in the view.
        // Note how we are using only 1 view for different books. Making use of the template and just filling in
        // the different values.
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "view-book";
    }

    @GetMapping("/add")
    public String addBookView(Model model) {
        // Render the page which allows a user to add a new book.
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // NOTE: There is a difference between GET and POST, hence we can use the same URL for both user actions.
    @PostMapping("/add")
    public RedirectView addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        // This code runs when the user clicks 'Submit' on the form.
        final RedirectView redirectView = new RedirectView("/books/");
        int bookSaved = bookService.addBook(book);
        Book savedBook = bookService.getBook(bookSaved);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        redirectAttributes.addFlashAttribute("savedBook", savedBook);
        return redirectView;
    }
}
