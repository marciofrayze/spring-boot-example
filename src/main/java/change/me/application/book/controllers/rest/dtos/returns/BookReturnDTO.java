package tech.segunda.application.book.controllers.rest.dtos.returns;

public class BookReturnDTO {

    private String isbn;
    private String title;
    private String description;

    public BookReturnDTO(
    		final String isbn,
            final String title,
            final String description) {

        this.isbn = isbn;
        this.title = title;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}