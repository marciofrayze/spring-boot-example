package change.me.infrastructure.book.services;

import change.me.infrastructure.book.converters.BookConverter;
import change.me.infrastructure.book.persistence.BookJpaRepository;
import change.me.infrastructure.book.persistence.entities.BookEntityJPA;
import org.springframework.stereotype.Service;
import change.me.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceJpa implements BookService
{
    private final BookJpaRepository bookJpaRepository;
    private final BookConverter bookConverter;

    public BookServiceJpa(
    		BookJpaRepository bookJpaRepository,
            BookConverter bookConverter)
    {
        this.bookJpaRepository = bookJpaRepository;
        this.bookConverter = bookConverter;
    }

    @Override
    public void persist(Book book)
    {
        BookEntityJPA bookEntity = bookConverter.convertToBookEntity(book);

        bookJpaRepository.save(bookEntity);
    }

    @Override
    public List<Book> findAll()
    {
        List<BookEntityJPA> booksEntities = bookJpaRepository.findAll();
        List<Book> result = bookConverter.convertToBooks(booksEntities);

        return result;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn)
    {
        Optional<BookEntityJPA> bookEntityJPA = bookJpaRepository.findByIsbn(isbn);

        if (bookEntityJPA.isPresent())
        {
            return Optional.of(bookConverter.convertToBook(bookEntityJPA.get()));
        } else {
            return Optional.empty();
        }

    }

	@Override
	public void removeAll()
    {
		bookJpaRepository.deleteAll();
	}

}