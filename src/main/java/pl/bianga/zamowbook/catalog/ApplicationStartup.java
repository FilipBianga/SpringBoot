package pl.bianga.zamowbook.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bianga.zamowbook.catalog.application.CatalogController;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogController catalogController;

    @Override
    public void run(String... args) throws Exception {
        //jest to metoda która po uruchomieniu aplikacji wykona dopiero ten tutaj kod
        List<Book> books = catalogController.findByTitle("Ogniem i Mieczem");
        books.forEach(System.out::println);
    }
}
