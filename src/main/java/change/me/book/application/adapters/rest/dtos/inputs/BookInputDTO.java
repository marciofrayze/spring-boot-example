package change.me.book.application.adapters.rest.dtos.inputs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

public class BookInputDTO {

    @NotBlank
    @Size(min = 13, max = 13)
    private String isbn;

    @NotBlank
    @Size(max = 256)
    private String title;

    @Size(max = 1024)
    private String description;

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