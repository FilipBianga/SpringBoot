package pl.bianga.zamowbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
