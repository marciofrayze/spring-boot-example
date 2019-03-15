package change.me.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import change.me.book.domain.Book;
import change.me.book.infrastructure.services.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class BookServiceFake implements BookService {

	private final List<Book> books;

	public BookServiceFake() {
		books = new ArrayList<>();
	}

	@Override
	public List<Book> findAll() {
		return books;
	}

	@Override
	public void persist(Book book) {
		books.add(book);
	}

	@Override
	public Optional<Book> findByIsbn(String isbn) {
		return books
				.stream()
				.filter(book -> book.getIsbn().equals(isbn))
				.findFirst();
	}

	@Override
	public void removeAll() {
		books.clear();
	}

}
