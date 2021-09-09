package pl.bianga.zamowbook.catalog.application;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.catalog.domain.CatalogRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class CatalogService implements CatalogUseCase {

    //prosta struktura danych, Mapa przechowywac bedzie w pamieci ksiazki, pierwszy argument to id drugi to ksiazka
//    private final Map<Long, Book> storage = new ConcurrentHashMap<>();
    private final CatalogRepository repository;

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    //Zwracamy listę książek
    @Override
    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))      //filtrujemy ksiazki po tytule wybranym przez uzytkownika
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .filter(book -> book.getAuthor().startsWith(author))
                .findFirst();

    }

    @Override
    public void  addBook(CreateBookCommand command) {
        Book book = new Book(command.getTitle(), command.getAuthor(), command.getYear());
        repository.save(book);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookCommand command) {
        return repository.findById(command.getId())
                .map(book -> {
                    book.setTitle(command.getTitle());
                    book.setAuthor(command.getAuthor());
                    book.setYear(command.getYear());
                    repository.save(book);
                    return UpdateBookResponse.SUCCESS;
                })
                        .orElseGet(() -> new UpdateBookResponse(false, Collections.singletonList("Book not found with id: " + command.getId())));
    }
}
