package by.home.eventOrganizer.service.detail;

import by.home.eventOrganizer.model.detail.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {

    Order findByIdWithFetches(Long id);

    List<Order> findAllWithFetches();

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
    Order findById(Long id);

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
