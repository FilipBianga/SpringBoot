package pl.bianga.zamowbook.catalog.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bianga.zamowbook.catalog.application.port.CatalogInitializerUseCase;
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
    private final CatalogInitializerUseCase initializer;

    @PostMapping("/initialization")
    @Transactional
    public void initialize(){
        initializer.initialize();
    }

}
