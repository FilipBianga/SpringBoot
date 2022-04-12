package pl.bianga.zamowbook.order.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bianga.zamowbook.order.domain.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
