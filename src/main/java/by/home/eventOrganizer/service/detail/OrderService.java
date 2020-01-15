package by.home.eventOrganizer.service.detail;

import by.home.eventOrganizer.model.detail.Order;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {


    /**
     * Fetches all list.
     *
     * @return the list
     */
    List<Order> fetchesAll();


    /**
     * Fetches by id order.
     *
     * @param id the id
     * @return the order
     */
    Order fetchesById(Long id);

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean existsById(Long id);

    /**
     * Save order.
     *
     * @param order the order
     * @return the order
     */
    Order save(Order order);

    /**
     * Find by id order.
     *
     * @param id the id
     * @return the order
     */
    Order findById(Long id);

    /**
     * Order staff salary by customer phone number double.
     *
     * @param number the number
     * @return the double
     */
    Double orderStaffSalaryByCustomerPhoneNumber(Long number);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Order> findAll();

    /**
     * Update order.
     *
     * @param order the order
     * @return the order
     */
    Order update(Order order);

    /**
     * Delete.
     *
     * @param order the order
     */
    void delete(Order order);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
