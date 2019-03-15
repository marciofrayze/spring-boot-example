package change.me.book.infrastructure.persistence;

import change.me.book.infrastructure.persistence.entities.BookEntityJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntityJPA, Long> {

    @Override
    Optional<BookEntityJPA> findById(Long id);

    Optional<BookEntityJPA> findByIsbn(String isbn);

}