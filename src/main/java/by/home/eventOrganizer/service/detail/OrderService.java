package by.home.eventOrganizer.service.detail;

import by.home.eventOrganizer.model.detail.Order;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Save.
     *
     * @param order the order
     */
    Order save(Order order);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Order> findById(Long id);

    /**
     * Order price by customer phone number double.
     *
     * @param number the number
     * @return the double
     */
    Double orderStaffSalaryByCustomerPhoneNumber(Long number);

    List<Order> findAll();

    Order update(Order order);

    void delete(Order order);

    void deleteById(Long id);
}
