package pl.bianga.zamowbook.order.domain;

import lombok.Setter;
import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import pl.bianga.zamowbook.jpa.BaseEntity;
import javax.persistence.Entity;

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
