package change.me.book.infrastructure.services;

import change.me.book.infrastructure.services.converters.BookConverter;
import org.springframework.stereotype.Service;
import change.me.book.domain.Book;
import change.me.book.infrastructure.persistence.BookJpaRepository;
import change.me.book.infrastructure.persistence.entities.BookEntityJPA;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceJpa implements BookService {

    private final BookJpaRepository bookJpaRepository;
    private final BookConverter bookConverter;

    public BookServiceJpa(
    		BookJpaRepository bookJpaRepository,
            BookConverter bookConverter) {

        this.bookJpaRepository = bookJpaRepository;
        this.bookConverter = bookConverter;
    }

    @Override
    public void persist(Book book) {

        final BookEntityJPA bookEntity = bookConverter.convertToBookEntity(book);

        bookJpaRepository.save(bookEntity);
    }

    @Override
    public List<Book> findAll() {

        List<BookEntityJPA> booksEntities = bookJpaRepository.findAll();

        List<Book> books = bookConverter.convertToBooks(booksEntities);

        return books;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {

        Optional<BookEntityJPA> bookEntityJPA = bookJpaRepository.findByIsbn(isbn);

        if (bookEntityJPA.isPresent()) {
            return Optional.of(bookConverter.convertToBook(bookEntityJPA.get()));
        } else {
            return Optional.empty();
        }

    }

	@Override
	public void removeAll() {
		bookJpaRepository.deleteAll();
	}

}