package pl.bianga.zamowbook.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.catalog.domain.Author;

public interface AuthorJpaRepository extends JpaRepository<Author, Long> {
}
