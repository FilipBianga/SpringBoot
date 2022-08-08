package pl.bianga.zamowbook.order.domain;

import lombok.*;
import pl.bianga.zamowbook.catalog.domain.Book;
import pl.bianga.zamowbook.jpa.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int quantity;
}
