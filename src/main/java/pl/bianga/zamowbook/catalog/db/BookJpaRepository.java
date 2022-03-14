package pl.bianga.zamowbook.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.catalog.domain.Book;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
