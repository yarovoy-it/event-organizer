package by.home.eventOrganizer.service.human;

import by.home.eventOrganizer.model.human.Customer;

import java.util.List;
import java.util.Optional;


/**
 * The interface Customer service .
 */
public interface CustomerService {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Customer> findAll();

    /**
     * Find customer by address street list.
     *
     * @param name the name
     * @return the list
     */
    List<Customer> findByAddress_Street(String name);

    /**
     * Find customer by phone number list.
     *
     * @param number the number
     * @return the list
     */
    List<Customer> findByPhoneNumber(Long number);

    /**
     * Find customer by name or surname list.
     *
     * @param name    the name
     * @param surname the surname
     * @return the list
     */
    List<Customer> findByNameOrSurname(String name, String surname);

    /**
     * Find customer by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Customer> findById(Long id);

    /**
     * Delete customer by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
