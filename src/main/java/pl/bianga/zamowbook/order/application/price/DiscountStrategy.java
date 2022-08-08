package pl.bianga.zamowbook.order.application.price;

import pl.bianga.zamowbook.order.domain.Order;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculate(Order order);
}
