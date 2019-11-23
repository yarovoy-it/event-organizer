package by.home.eventOrganizer.repository.detail;

import by.home.eventOrganizer.model.detail.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
