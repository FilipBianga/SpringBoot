package pl.bianga.zamowbook.order.domain;

import lombok.*;
import pl.bianga.zamowbook.jpa.BaseEntity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipient extends BaseEntity{

    private String email;
    private String name;
    private String phone;
    private String street;
    private String city;
    private String zipCode;

}
