package change.me.book.application.adapters.rest;

import change.me.book.application.adapters.rest.dtos.inputs.BookInputDTO;
import change.me.book.application.adapters.rest.dtos.returns.BookReturnDTO;
import change.me.book.application.exceptions.ResourceNotFoundException;
import change.me.book.infrastructure.services.BookService;
import change.me.book.infrastructure.services.converters.BookConverter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import change.me.book.domain.Book;

@RestController
@RequestMapping(
        path = "/api/books",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRestController
{
    private final BookConverter bookConverter;
    private final BookService bookService;

    public BooksRestController(
			BookService bookService,
			BookConverter bookConverter)
    {
        this.bookConverter = bookConverter;
        this.bookService = bookService;
    }

    @PostMapping
    public BookReturnDTO create(@Valid @RequestBody BookInputDTO bookInputDTO)
    {
        Book book = bookConverter.convertToBook(bookInputDTO);
        bookService.persist(book);

        return bookConverter.convertToBookReturnDTO(book);
    }

    @GetMapping
    public List<BookReturnDTO> getAll()
    {
        List<Book> books = bookService.findAll();

        return bookConverter.convertToBooksReturnDTOs(books);
    }

    @GetMapping("/{isbn}")
    public BookReturnDTO getByIsbn(@PathVariable("isbn") String isbn)
    {
        Optional<Book> book = bookService.findByIsbn(isbn);

        if (book.isPresent())
        {
			return bookConverter.convertToBookReturnDTO(book.get());
        } else {
			throw new ResourceNotFoundException();
        }
    }

}