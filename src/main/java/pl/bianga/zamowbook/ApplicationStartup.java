package pl.bianga.zamowbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.CreateBookCommand;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase.UpdateBookResponse;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.order.application.port.PlaceOrderUseCase;
import pl.bianga.zamowbook.order.application.port.PlaceOrderUseCase.PlaceOrderCommand;
import pl.bianga.zamowbook.order.application.port.PlaceOrderUseCase.PlaceOrderResponse;
import pl.bianga.zamowbook.order.application.port.QueryOrderUseCase;
import pl.bianga.zamowbook.order.domain.OrderItem;
import pl.bianga.zamowbook.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final PlaceOrderUseCase placeOrder;
    private final QueryOrderUseCase queryOrder;
    private final String title;


    public ApplicationStartup(
            CatalogUseCase catalog,
            PlaceOrderUseCase placeOrder,
            QueryOrderUseCase queryOrder,
            @Value("${zamowbook.catalog.title}") String title) {
        this.catalog = catalog;
        this.placeOrder = placeOrder;
        this.queryOrder = queryOrder;
        this.title = title;

    }

    @Override
    public void run(String... args) {
        initData();
        searchCatalog();
        placeOrder();
    }

    private void placeOrder() {
        Book sezonBurz = catalog.findOneByTitle("Sezon burz").orElseThrow(() -> new IllegalStateException("Cannot find a book"));
        Book harryPotter = catalog.findOneByTitle("Harry Potter - Kamien Filozoficzny").orElseThrow(() -> new IllegalStateException("Cannot find a book"));

        Recipient recipient = Recipient
                .builder()
                .name("Zbigniew Golgota")
                .phone("888-908-765")
                .street("Kasztanowa 5/13")
                .city("Gdansk")
                .zipCode("80-800")
                .email("zgol@gmail.com")
                .build();

        PlaceOrderCommand command = PlaceOrderCommand
                .builder()
                .recipient(recipient)
                .item(new OrderItem(sezonBurz, 16))
                .item(new OrderItem(harryPotter, 12))
                .build();
        PlaceOrderResponse response = placeOrder.placeOrder(command);
        System.out.println("Created ORDER with id: " + response.getOrderId());

        queryOrder.findAll()
                .forEach(order -> {
                    System.out.println("GOT ORDER WITH TOTAL PRICE: " + order.totalPrice() + " DETAILS: " + order);
                });
    }

    private void searchCatalog() {
        findByTitle();
        findAndUpdate();
        findByTitle();
    }

    private void initData() {
        catalog.addBook(new CreateBookCommand("Harry Potter - Kamien Filozoficzny", "J.K. Rowling", 1997, new BigDecimal("50.99")));
        catalog.addBook(new CreateBookCommand("Władca Pierścieni - Dwie wierze", "J.R.R. Tolkien", 1954, new BigDecimal("80.99")));
        catalog.addBook(new CreateBookCommand("Sezon burz", "Andrzej Sapkowski", 2013, new BigDecimal("70.99")));
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
