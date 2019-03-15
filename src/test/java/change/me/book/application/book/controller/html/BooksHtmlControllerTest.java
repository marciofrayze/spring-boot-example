package change.me.book.application.book.controller.html;

import change.me.book.infrastructure.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import change.me.book.domain.Book;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({
		RestDocumentationExtension.class,
		SpringExtension.class
})
@SpringBootTest
public class BooksHtmlControllerTest
{
	private MockMvc mockMvc;

	@Autowired
    private BookService bookService;

	@BeforeEach
	public void setUpBeforeEach(
			WebApplicationContext webApplicationContext,
			RestDocumentationContextProvider restDocumentation)
	{
		bookService.removeAll();

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
				.build();
	}

	@Test
	public void getBooks_shouldReturnPageWithResumeOfAllBoks() throws Exception
	{
		// Given
		Book someBook = new Book(
				"1234567890123",
				"A fake book",
				"Just a fake book");
		bookService.persist(someBook);

		// When
		ResultActions result = this.mockMvc.perform(
				get("/books/")
		);

 		// Then
		String expectedPageText = "Total number of books: 1";
		result.andExpect(
				status()
						.isOk())
				.andExpect(
						content().string(
								containsString(expectedPageText)
						)
				);
	}
}