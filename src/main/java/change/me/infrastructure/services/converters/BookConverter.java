package change.me.infrastructure.services.converters;

import change.me.application.book.controllers.rest.dtos.inputs.BookInputDTO;
import change.me.application.book.controllers.rest.dtos.returns.BookReturnDTO;
import org.springframework.stereotype.Service;
import change.me.domain.Book;
import change.me.infrastructure.persistence.entities.BookEntityJPA;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookConverter {

    public Book convertToBook(BookInputDTO bookInputDTO) {

        return new Book(
        		bookInputDTO.getIsbn(),
                bookInputDTO.getTitle(),
                bookInputDTO.getDescription().orElse(null));
    }

    public BookEntityJPA convertToBookEntity(Book book) {

		BookEntityJPA bookEntityJPA = new BookEntityJPA();
		bookEntityJPA.setTitle(book.getTitle());
		bookEntityJPA.setIsbn(book.getIsbn());
		bookEntityJPA.setDescription(book.getDescription().orElse(null));

		return bookEntityJPA;
    }

    public Book convertToBook(BookEntityJPA bookEntityJPA) {

        return new Book(
        		bookEntityJPA.getIsbn(),
                bookEntityJPA.getTitle(),
                bookEntityJPA.getDescription());

    }

    public BookReturnDTO convertToBookReturnDTO(Book book) {

        return new BookReturnDTO(
        		book.getIsbn(),
                book.getTitle(),
                book.getDescription().orElse(""));
    }

    public List<BookReturnDTO> convertToBooksReturnDTOs(List<Book> books) {

        return books.stream()
                .map(book ->  convertToBookReturnDTO(book))
                .collect(Collectors.toList());
    }

    public List<Book> convertToBooks(List<BookEntityJPA> booksEntities) {

        return booksEntities.stream()
                .map(bookEntityJPA ->  convertToBook(bookEntityJPA))
                .collect(Collectors.toList());
    }

}
