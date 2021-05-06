package pl.bianga.zamowbook.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bianga.zamowbook.catalog.application.CatalogController;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogController catalogController;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationStartup(CatalogController catalogController,
                              @Value("${zamowbook.catalog.title}") String title,
                              @Value("${zamowbook.catalog.limit}") Long limit,
                              @Value("${zamowbook.catalog.author}") String author) {
        this.catalogController = catalogController;
        this.title = title;
        this.limit = limit;
        this.author = author;
    }

    @Override
    public void run(String... args) throws Exception {
        //jest to metoda która po uruchomieniu aplikacji wykona dopiero ten tutaj kod
        List<Book> books = catalogController.findByTitle(title);
        books.stream().limit(limit).forEach(System.out::println);
        System.out.println("-------------------------------------");
        List<Book> booksFindByAuthor = catalogController.findByAuthor(author);
        booksFindByAuthor.forEach(System.out::println);
    }
}
