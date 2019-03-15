package change.me.book.infrastructure.persistence.entities;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Audited
@SequenceGenerator(name = "seq_books", sequenceName = "seq_books", allocationSize = 1)
@Table(name = "books")
public class BookEntityJPA
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_books")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false)
    private Date createdAt;

    @Column(
            length = 13,
            nullable = false,
            unique=true,
            updatable = false)
    @Size(
            min = 13,
            max=13)
    private String isbn;

    @Column(
            length = 256,
            nullable = false)
    private String title;

    @Column(length = 1024)
    private String description;

    public BookEntityJPA()
    {
        // Empty constructor just to make JPA happy
    }

    public BookEntityJPA(String title, String isbn, String description)
    {
        this.title = title;
        this.isbn = isbn;
        this.description = description;
    }

    public BookEntityJPA(String title, String isbn)
    {
        this(title, isbn, null);
    }

    @PrePersist
    private void onCreate()
    {
        createdAt = new Date();
    }

	public String getIsbn()
    {
		return isbn;
	}

	public void setIsbn(String isbn)
    {
		this.isbn = isbn;
	}

	public String getTitle()
    {
		return title;
	}

	public void setTitle(String title)
    {
		this.title = title;
	}

	public String getDescription()
    {
		return description;
	}

	public void setDescription(String description)
    {
		this.description = description;
	}
}