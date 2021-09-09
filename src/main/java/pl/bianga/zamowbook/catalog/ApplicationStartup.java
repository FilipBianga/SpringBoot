package pl.bianga.zamowbook.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.CreateBookCommand;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.bianga.zamowbook.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalogUseCase;
    private final String title;


    public ApplicationStartup(CatalogUseCase catalog,
                              @Value("${zamowbook.catalog.title}") String title) {
        this.catalogUseCase = catalog;
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
        catalogUseCase.addBook(new CreateBookCommand("Harry Potter - Kamien Filozoficzny", "J.K. Rowling", 1997));
        catalogUseCase.addBook(new CreateBookCommand("Władca Pierścieni - Dwie wierze", "J.R.R. Tolkien", 1954));
        catalogUseCase.addBook(new CreateBookCommand("Sezon burz", "Andrzej Sapkowski", 2013));
    }

    private void findByTitle(){
        //jest to metoda która po uruchomieniu aplikacji wykona dopiero ten tutaj kod
        List<Book> books = catalogUseCase.findByTitle(title);
        books.forEach(System.out::println);
    }

    private void findAndUpdate() {
        System.out.println("What?....");
        catalogUseCase.findOneByTitleAndAuthor("Harry Potter - Kamien Filozoficzny", "J.K. Rowling")
                .ifPresent(book -> {
                    UpdateBookCommand command = new UpdateBookCommand(
                            book.getId(),
                            "Harry Potter - Komnata Tajemnic",
                            book.getAuthor(),
                            book.getYear()
                    );
                    catalogUseCase.updateBook(command);
                });
    }
}
