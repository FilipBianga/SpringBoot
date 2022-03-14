package pl.bianga.zamowbook.order.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {
    String name;
    String phone;
    String street;
    String city;
    String zipCode;
    String email;

}
