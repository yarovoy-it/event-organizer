package by.home.eventOrganizer.service.detail;

import by.home.eventOrganizer.model.detail.Order;

import java.util.Optional;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Save transient order and generate price based on staff, goods and beverage costs.
     *
     * @param order the order
     */
    void saveTransientOrder(Order order);

    /**
     * Save.
     *
     * @param order the order
     */
    void save(Order order);

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
}
