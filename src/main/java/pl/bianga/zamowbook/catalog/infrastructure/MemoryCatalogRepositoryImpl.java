package pl.bianga.zamowbook.catalog.infrastructure;

import org.springframework.stereotype.Repository;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryCatalogRepositoryImpl implements CatalogRepository {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public MemoryCatalogRepositoryImpl() {
        storage.put(1L, new Book(1L, "Pan Tadeusz", "Adam Mickiewicz", 1834));
        storage.put(2L, new Book(2L, "Ogniem i Mieczem", "Henryk Sienkiewicz", 1884));
        storage.put(3L, new Book(3L, "Chłopi", "Władysław Reymont", 1904));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
}
