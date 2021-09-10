package pl.bianga.zamowbook.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.CreateBookCommand;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.UpdateBookResponse;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final String title;


    public ApplicationStartup(CatalogUseCase catalog,
                              @Value("${zamowbook.catalog.title}") String title) {
        this.catalog = catalog;
        this.title = title;

    }

    @Override
    public void run(String... args) {
        initData();
        findByTitle();
        findAndUpdate();
        findByTitle();
    }

    private void initData() {
        catalog.addBook(new CreateBookCommand("Harry Potter - Kamien Filozoficzny", "J.K. Rowling", 1997));
        catalog.addBook(new CreateBookCommand("Władca Pierścieni - Dwie wierze", "J.R.R. Tolkien", 1954));
        catalog.addBook(new CreateBookCommand("Sezon burz", "Andrzej Sapkowski", 2013));
    }

    private void findByTitle(){
        List<Book> books = catalog.findByTitle(title);
        books.forEach(System.out::println);
    }

    private void findAndUpdate() {
        catalog.findOneByTitleAndAuthor("Sezon burz", "Andrzej Sapkowski")
                .ifPresent(book -> {
                    UpdateBookCommand command = UpdateBookCommand
                            .builder()
                            .id(book.getId())
                            .title("Sezon burz, ale i deszczy")
                            .build();
                    UpdateBookResponse response = catalog.updateBook(command);
                    System.out.println(response);
                });
    }
}
