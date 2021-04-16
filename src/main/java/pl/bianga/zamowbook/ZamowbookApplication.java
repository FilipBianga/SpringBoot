package pl.bianga.zamowbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ZamowbookApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZamowbookApplication.class, args);
    }

    private final CatalogService catalogService;

    public ZamowbookApplication(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public void run(String... args) throws Exception {
        //jest to metoda która po uruchomieniu aplikacji wykona dopiero ten tutaj kod
        List<Book> books = catalogService.findByTitle("Ogniem i Mieczem");
        books.forEach(System.out::println);
    }
}
