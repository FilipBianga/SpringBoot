package pl.bianga.zamowbook.order.application.port;

import pl.bianga.zamowbook.order.domain.Order;

import java.util.List;

public interface QueryOrderUseCase {
    List<Order> findAll();
}
