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
     * Order salary only for staff by customer by phone number double.
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

    @Query(value = "select sum(st.salary) from staff st join orders_staff ord_st on st.id = ord_st.staff_id join orders ord on ord.id = ord_st.order_id where ord.id = :id", nativeQuery = true)
    Double getSalaryByOrderById(@Param("id") Long id);


    @Query(value = "select sum(bv.price * bv.count) from beverages bv join orders_beverages ord_bv on bv.id = ord_bv.beverage_id join orders ord on ord.id = ord_bv.order_id  where ord.id = :id", nativeQuery = true)
    Double getBeveragePriceByOrderById(@Param("id") Long id);

    @Query(value = "select sum(gds.price * gds.count) from goods gds join orders_goods ord_gds on gds.id = ord_gds.goods_id join orders ord on ord.id = ord_gds.order_id where ord.id = :id", nativeQuery = true)
    Double getGoodsPriceByOrderById(@Param("id") Long id);


    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.address  " +
            "JOIN FETCH o.customer " +
            "JOIN FETCH o.goods " +
            "JOIN FETCH o.beverages " +
            "JOIN FETCH o.staff")
    List<Order> findAllWithFetches();

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.address " +
            "JOIN FETCH o.customer " +
            "LEFT JOIN FETCH o.goods " +
            "LEFT JOIN FETCH o.beverages " +
            "LEFT JOIN FETCH o.staff " +
            "where o.id= :id")
    Order findByIdWithFetches(@Param("id") Long id);


}
