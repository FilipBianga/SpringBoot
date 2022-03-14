package pl.bianga.zamowbook.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import pl.bianga.zamowbook.catalog.domain.Book;

@Value
@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
    Long bookId;
    private int quantity;
}
