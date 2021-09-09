package pl.bianga.zamowbook.catalog.application.port;

import lombok.Value;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface CatalogUseCase {
    //Zwracamy listę książek
    List<Book> findByTitle(String title);

    List<Book> findAll();

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    void addBook(CreateBookCommand command);

    void removeById(Long id);

    UpdateBookResponse updateBook(UpdateBookCommand command);

    @Value
    class CreateBookCommand {
        String title;
        String author;
        Integer year;
    }

    @Value
    class UpdateBookCommand {
        Long id;
        String title;
        String author;
        Integer year;
    }

    @Value
    class UpdateBookResponse {
        public static UpdateBookResponse SUCCESS = new UpdateBookResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}
