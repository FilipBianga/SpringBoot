package pl.bianga.zamowbook.catalog.domain;

import java.util.List;

public interface CatalogRepository {
    List<Book> findAll();
}
