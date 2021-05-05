package pl.bianga.zamowbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.bianga.zamowbook.catalog.application.CatalogController;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.catalog.domain.CatalogService;

import java.util.List;

@SpringBootApplication
public class ZamowbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZamowbookApplication.class, args);
    }

//    private final CatalogController catalogController;
//
//    public ZamowbookApplication(CatalogController catalogController) {
//        this.catalogController = catalogController;
//    }

//    @Override
//    public void run(String... args) throws Exception {
//        //jest to metoda która po uruchomieniu aplikacji wykona dopiero ten tutaj kod
//        List<Book> books = catalogController.findByTitle("Ogniem i Mieczem");
//        books.forEach(System.out::println);
//    }
}
