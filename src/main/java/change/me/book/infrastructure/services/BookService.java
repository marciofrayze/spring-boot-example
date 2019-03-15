package change.me.book.infrastructure.services;

import change.me.book.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService
{
    void persist(Book book);

    List<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    void removeAll();

}