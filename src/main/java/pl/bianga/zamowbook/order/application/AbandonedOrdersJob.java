package pl.bianga.zamowbook.order.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bianga.zamowbook.clock.Clock;
import pl.bianga.zamowbook.order.application.port.ManipulateOrderUseCase;
import pl.bianga.zamowbook.order.db.OrderJpaRepository;
import pl.bianga.zamowbook.order.domain.Order;
import pl.bianga.zamowbook.order.domain.OrderStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static pl.bianga.zamowbook.order.application.port.ManipulateOrderUseCase.*;

@Slf4j
@Component
@AllArgsConstructor
public class AbandonedOrdersJob {
    private final OrderJpaRepository repository;
    private final ManipulateOrderUseCase orderUseCase;
    private final OrdersProperties properties;
    private final Clock clock;


    @Transactional
    @Scheduled(cron = "${app.orders.abandon-cron}")  //co 60 sekund sprawdzanie
    public void run() {
        Duration paymentPeriod = properties.getPaymentPeriod();
        LocalDateTime olderThan = clock.now().minus(paymentPeriod);
        List<Order> orders = repository.findByStatusAndCreatedAtLessThanEqual(OrderStatus.NEW, olderThan);
        log.info("Found orders to be abandoned: " + orders.size());
        orders.forEach(order -> {
            // TODO-Darek: naprawic w module security
            String adminEmail = "admin@example.org";
            UpdateStatusCommand command = new UpdateStatusCommand(order.getId(), OrderStatus.ABANDONED, adminEmail);
            orderUseCase.updateOrderStatus(command);
        });
    }
}
