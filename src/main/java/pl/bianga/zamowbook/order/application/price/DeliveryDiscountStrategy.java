package pl.bianga.zamowbook.order.application.price;

import pl.bianga.zamowbook.order.domain.Order;

import java.math.BigDecimal;

public class DeliveryDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal calculate(Order order) {
        return null;
    }
}
