package pl.bianga.zamowbook.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthors_firstNameContainsIgnoreCaseOrAuthors_lastNameContainsIgnoreCase(String firstName, String lastName);

    List<Book> findByTitleStartsWithIgnoreCase(String title);

    Optional<Book> findDistinctFirstByTitleIgnoreCase(String title);
}
