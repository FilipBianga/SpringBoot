package pl.bianga.zamowbook.catalog.infrastructure;

import org.springframework.stereotype.Repository;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class BestsellerCatalogRepositoryImpl implements CatalogRepository {
    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public BestsellerCatalogRepositoryImpl() {
        storage.put(1L, new Book(1L, "Harry Potter - Kamień Filozoficzny", "J.K. Rowling ", 1997));
        storage.put(2L, new Book(2L, "Władca Pierścieni - Dwie wierze", "J.R.R. Tolkien", 1954));
        storage.put(3L, new Book(3L, "Sezon burz", "Andrzej Sapkowski", 2013));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
}
