package pl.bianga.zamowbook.order.application;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bianga.zamowbook.order.application.port.ManipulateOrderUseCase;
import pl.bianga.zamowbook.order.db.OrderJpaRepository;
import pl.bianga.zamowbook.order.domain.Order;
import pl.bianga.zamowbook.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class AbandonedOrdersJob {
    private final OrderJpaRepository repository;
    private final ManipulateOrderUseCase orderUseCase;

    @Transactional
    @Scheduled(fixedRate = 60_000)  //co 60 sekund sprawdzanie
    public void run() {
        LocalDateTime timestamp = LocalDateTime.now().minusMinutes(5); // co 5 minut dla produkcji lepiej sie zastanowic i dac w dniach
        List<Order> orders = repository.findByStatusAndCreatedAtLessThanEqual(OrderStatus.NEW ,timestamp);
        System.out.println("Found orders to be abandoned: " + orders.size());
        orders.forEach(order -> orderUseCase.updateOrderStatus(order.getId(), OrderStatus.ABANDONED));
    }
}
