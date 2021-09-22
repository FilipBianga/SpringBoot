package pl.bianga.zamowbook.order.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bianga.zamowbook.order.application.port.QueryOrderUseCase;
import pl.bianga.zamowbook.order.domain.Order;
import pl.bianga.zamowbook.order.domain.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {
    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}
