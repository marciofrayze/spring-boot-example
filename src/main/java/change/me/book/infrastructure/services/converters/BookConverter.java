package change.me.book.infrastructure.services.converters;

import change.me.book.application.adapters.rest.dtos.inputs.BookInputDTO;
import change.me.book.application.adapters.rest.dtos.returns.BookReturnDTO;
import org.springframework.stereotype.Service;
import change.me.book.domain.Book;
import change.me.book.infrastructure.persistence.entities.BookEntityJPA;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookConverter
{
    public Book convertToBook(BookInputDTO bookInputDTO)
    {
        return new Book(
        		bookInputDTO.getIsbn(),
                bookInputDTO.getTitle(),
                bookInputDTO.getDescription().orElse(null));
    }

    public BookEntityJPA convertToBookEntity(Book book)
    {
		return new BookEntityJPA(
		        book.getTitle(),
                book.getIsbn(),
                book.getDescription().orElse(null));
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
