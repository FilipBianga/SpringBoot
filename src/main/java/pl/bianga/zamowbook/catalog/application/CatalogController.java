package pl.bianga.zamowbook.catalog.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.catalog.domain.CatalogService;

import java.util.List;

@Controller
@RequiredArgsConstructor //tworzy nam constructor
public class CatalogController {
    private final CatalogService service;

    public List<Book> findByTitle(String title) {
        return service.findByTitle((title));
    }

    public List<Book> findByAuthor(String author) {
        return service.findByAuthor((author));
    }
}
