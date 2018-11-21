package tech.segunda.application.book.controllers.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.segunda.application.book.controllers.rest.dtos.inputs.BookInputDTO;
import tech.segunda.application.book.controllers.rest.dtos.returns.BookReturnDTO;
import tech.segunda.application.exceptions.ResourceNotFoundException;
import tech.segunda.domain.Book;
import tech.segunda.infrastructure.services.BookService;
import tech.segunda.infrastructure.services.converters.BookConverter;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/books",
                produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRestController {

    private final BookConverter bookConverter;
    private final BookService bookService;

    public BooksRestController(
			BookService bookService,
			BookConverter bookConverter) {

        this.bookConverter = bookConverter;
        this.bookService = bookService;
    }

    @PostMapping
    public BookReturnDTO create(@Valid @RequestBody BookInputDTO bookInputDTO) {

        final Book book = bookConverter.convertToBook(bookInputDTO);

        bookService.persist(book);

        return bookConverter.convertToBookReturnDTO(book);
    }

    @GetMapping
    public List<BookReturnDTO> getAll() {

        List<Book> books = bookService.findAll();

        return bookConverter.convertToBooksReturnDTOs(books);
    }

    @GetMapping("/{isbn}")
    public BookReturnDTO getByIsbn(@PathVariable("isbn") String isbn) {

        Optional<Book> book = bookService.findByIsbn(isbn);

        if (book.isPresent()) {
			return bookConverter.convertToBookReturnDTO(book.get());
        } else {
			throw new ResourceNotFoundException();
        }

    }

}