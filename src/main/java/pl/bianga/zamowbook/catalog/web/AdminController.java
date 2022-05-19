package pl.bianga.zamowbook.catalog.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bianga.zamowbook.catalog.application.port.CatalogUseCase;
import pl.bianga.zamowbook.catalog.db.AuthorJpaRepository;
import pl.bianga.zamowbook.catalog.domain.Author;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.order.application.port.ManipulateOrderUseCase;
import pl.bianga.zamowbook.order.application.port.QueryOrderUseCase;
import pl.bianga.zamowbook.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final CatalogUseCase catalog;
    private final ManipulateOrderUseCase placeOrder;
    private final QueryOrderUseCase queryOrder;
    private final AuthorJpaRepository authorRepository;

    @PostMapping("/data")
    @Transactional
    public void initialize(){
        initData();
        placeOrder();
    }

    private void placeOrder() {
        Book effectiveJava = catalog.findOneByTitle("Effective Java")
                .orElseThrow(() -> new IllegalStateException("Cannot find a book"));
        Book javaPuzzlers = catalog.findOneByTitle("Java Puzzlers")
                .orElseThrow(() -> new IllegalStateException("Cannot find a book"));

        Recipient recipient = Recipient
                .builder()
                .name("Zbigniew Golgota")
                .phone("888-908-765")
                .street("Kasztanowa 5/13")
                .city("Gdansk")
                .zipCode("80-800")
                .email("zgol@gmail.com")
                .build();

        ManipulateOrderUseCase.PlaceOrderCommand command = ManipulateOrderUseCase.PlaceOrderCommand
                .builder()
                .recipient(recipient)
                .item(new ManipulateOrderUseCase.OrderItemCommand(effectiveJava.getId(), 16))
                .item(new ManipulateOrderUseCase.OrderItemCommand(javaPuzzlers.getId(), 12))
                .build();
        ManipulateOrderUseCase.PlaceOrderResponse response = placeOrder.placeOrder(command);
        String result = response.handle(
                orderId -> "Created ORDER with id: " + orderId,
                error -> "Failed to created order: " + error
        );
        log.info(result);

        // list all orders
        queryOrder.findAll()
                .forEach(order -> System.out.println("GOT ORDER WITH TOTAL PRICE: " + order.totalPrice() + " DETAILS: " + order));
    }


    private void initData() {
        Author joshua = new Author("Joshua", "Bloch");
        Author neal = new Author("Neal", "Gafter");
        authorRepository.save(joshua);
        authorRepository.save(neal);

        CatalogUseCase.CreateBookCommand effectiveJava = new CatalogUseCase.CreateBookCommand(
                "Effective Java",
                Set.of(joshua.getId()),
                2005,
                new BigDecimal("79.00"),
                50L
        );
        CatalogUseCase.CreateBookCommand javaPuzzlers = new CatalogUseCase.CreateBookCommand(
                "Java Puzzlers",
                Set.of(joshua.getId(), neal.getId()),
                2018,
                new BigDecimal("99.00"),
                50L
        );
        catalog.addBook(effectiveJava);
        catalog.addBook(javaPuzzlers);
    }
}
