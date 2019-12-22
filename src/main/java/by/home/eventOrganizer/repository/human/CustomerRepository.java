package by.home.eventOrganizer.repository.human;

import by.home.eventOrganizer.model.human.Customer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Customer repository.
 */
public interface CustomerRepository extends PersonRepository<Customer, Long> {

    /**
     * Find all customers with fetch address.
     * used HQL
     *
     * @return the list of customer
     */
    @Query("SELECT cst FROM Customer cst JOIN FETCH cst.address")
    List<Customer> findAllWithFetch();
}
