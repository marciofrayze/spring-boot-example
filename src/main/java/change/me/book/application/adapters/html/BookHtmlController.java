package change.me.book.application.adapters.html;

import change.me.book.domain.Book;
import change.me.book.infrastructure.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookHtmlController {

    private final BookService bookService;

	private final String BOOK_MODEL_NAME = "books";
    private final String BOOK_TEMPLATE_NAME = "books";

    public BookHtmlController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String tickets(Model model) {

    	List<Book> books = bookService.findAll();

        model.addAttribute(BOOK_MODEL_NAME, books);

        return BOOK_TEMPLATE_NAME;
    }

}
