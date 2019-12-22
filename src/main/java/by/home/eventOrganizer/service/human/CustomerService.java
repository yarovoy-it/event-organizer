package by.home.eventOrganizer.service.human;

import by.home.eventOrganizer.model.human.Customer;

import java.util.List;


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
     * Find all with fetch address.
     * used JPQL
     *
     * @return the list of customers
     */
    List<Customer> findAllWithFetch();

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
    Customer findById(Long id);

    /**
     * Delete customer by id.
     *
     * @param id the id
     */
    void deleteById(Long id);

    /**
     * Save customer.
     *
     * @param customer the customer
     * @return the customer
     */
    Customer save(Customer customer);

    /**
     * Update customer.
     *
     * @param customer the customer
     * @return the customer
     */
    Customer update(Customer customer);

    /**
     * Delete.
     *
     * @param customer the customer
     */
    void delete(Customer customer);

}
