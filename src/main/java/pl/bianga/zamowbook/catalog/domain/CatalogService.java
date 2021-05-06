package pl.bianga.zamowbook.catalog.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service

public class CatalogService {

    //prosta struktura danych, Mapa przechowywac bedzie w pamieci ksiazki, pierwszy argument to id drugi to ksiazka
//    private final Map<Long, Book> storage = new ConcurrentHashMap<>();
    private final CatalogRepository repository;

    // Wstrzykiwanie konkretnego repo gdyz apka sie zwiesza gdy bysmy chcieli jednoczesnie dwa dlatego definiujemy ktore
    public CatalogService(@Qualifier("schoolCatalogRepositoryImpl") CatalogRepository repository) {
        this.repository = repository;
    }

    //tworzymy konstruktor z ksiazkami

//    public CatalogService(CatalogRepository repository) {
//        this.repository = repository;
// //        storage.put(1L, new Book(1L, "Pan Tadeusz", "Adam Mickiewicz", 1834));
// //        storage.put(2L, new Book(2L, "Ogniem i Mieczem", "Henryk Sienkiewicz", 1884));
// //        storage.put(3L, new Book(3L, "Chłopi", "Władysław Reymont", 1904));
//    }

    //Zwracamy listę książek
    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))      //filtrujemy ksiazki po tytule wybranym przez uzytkownika
                .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getAuthor().startsWith(author))
                .collect(Collectors.toList());
    }
}
