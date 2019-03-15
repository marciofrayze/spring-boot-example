package change.me.book.application.adapters.rest;

import change.me.book.infrastructure.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import change.me.book.domain.Book;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class BooksRestControllerTest {

	@Autowired
	private BookService bookService;

	private MockMvc mockMvc;

	private Book book1;
	private Book book2;

	@BeforeEach
	public void setUpBeforeEach(
			WebApplicationContext webApplicationContext,
			RestDocumentationContextProvider restDocumentation) {

		bookService.removeAll();
		book1 = new Book("0123456789123", "Fake Book", "Optional fake description");
		book2 = new Book("0987654321098", "Another Fake Book", null);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
				.build();
	}

	@Test
	public void getBooks_shouldReturnAllBooks() throws Exception {
		// Given
		bookService.persist(book1);
		bookService.persist(book2);

		// When / Then
		String exceptedReturnBooksAsJson = "[{\"isbn\":\"0123456789123\",\"title\":\"Fake Book\",\"description\":\"Optional fake description\"},{\"isbn\":\"0987654321098\",\"title\":\"Another Fake Book\",\"description\":\"\"}]";

		this.mockMvc.perform(get("/api/books/"))
				.andExpect(
						status().isOk())
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(
						content().string(exceptedReturnBooksAsJson));
	}

	@Test
	public void getBookByIsbn_shouldReturnCorrespondingBook() throws Exception {
		// Given
		bookService.persist(book1);
		bookService.persist(book2);

		// When / then
		this.mockMvc.perform(get("/api/books/0123456789123"))
				    .andExpect(
				    		status().isOk()
					);
	}

	@Test
	public void postBook_shouldAddBook() throws Exception {
		// Given
		String bookAsJsonStringContent = "{\"isbn\":\"0123456789123\",\"title\":\"Fake Book\",\"description\":\"Optional fake description\"}";

		// When / then
		this.mockMvc.perform(post("/api/books/")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(bookAsJsonStringContent))
					.andExpect(
							status().isOk()
					);

	}

}
