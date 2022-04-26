package pl.bianga.zamowbook.order.domain;

import lombok.*;
import pl.bianga.zamowbook.jpa.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{
    private Long bookId;
    private int quantity;
}
