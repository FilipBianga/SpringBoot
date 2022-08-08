package pl.bianga.zamowbook.catalog.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bianga.zamowbook.catalog.application.port.AuthorsUseCase;
import pl.bianga.zamowbook.catalog.db.AuthorJpaRepository;
import pl.bianga.zamowbook.catalog.domain.Author;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorsService implements AuthorsUseCase {
    private final AuthorJpaRepository repository;

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }
}
