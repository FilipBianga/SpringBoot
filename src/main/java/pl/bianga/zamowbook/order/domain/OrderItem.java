package pl.bianga.zamowbook.order.domain;

import lombok.Value;
import pl.bianga.zamowbook.catalog.domain.Book;

@Value
public class OrderItem {
    Book book;
    int quantity;
}
