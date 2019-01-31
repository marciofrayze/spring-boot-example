package tech.segunda.domain;

import java.util.Optional;

public class Book {

    private final String isbn;
    private final String title;
    private final String description;

    public Book(
    		String isbn,
            String title,
            String description) {

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

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

}