package by.home.eventOrganizer.repository.detail;

import by.home.eventOrganizer.model.detail.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface Order repository.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find order by customer phone number list.
     *
     * @param phoneNumber the phone number
     * @return the list
     */
    List<Order> findByCustomer_PhoneNumber(Long phoneNumber);

    /**
     * Order price by customer phone number double.
     * sql language
     * @param phoneNumber the phone number
     * @return the double
     */
    @Query(value = "select sum(salary) from (((staff st " +
            "join orders_staff ord_st on ord_st.staff_id = st.id) " +
            "join orders ord on ord.id = ord_st.order_id) " +
            "join customers cust on cust.id = ord.customer_id) " +
            "where cust.phone_number = :phnumber", nativeQuery = true)
    Double orderPriceByCustomerPhoneNumber(@Param("phnumber") Long phoneNumber);

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.address  " +
            "JOIN FETCH o.customer JOIN FETCH o.goods " +
            "JOIN FETCH o.beverages " +
            "JOIN FETCH o.staff")
    List<Order> findAllWithFetches();

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.address " +
            "JOIN FETCH o.customer " +
            "JOIN FETCH o.customer " +
            "JOIN FETCH o.goods " +
            "JOIN FETCH o.beverages " +
            "JOIN FETCH o.staff " +
            "where o.id= :id")
    Order findByIdWithFetches(@Param("id") Long id);
}
