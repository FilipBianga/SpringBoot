package pl.bianga.zamowbook.catalog.application.port;

import pl.bianga.zamowbook.catalog.domain.Author;

import java.util.List;

public interface AuthorsUseCase {
    List<Author> findAll();
}
