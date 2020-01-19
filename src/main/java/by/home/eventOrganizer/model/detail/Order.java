package by.home.eventOrganizer.model.detail;

import by.home.eventOrganizer.model.base.BaseEntity;
import by.home.eventOrganizer.model.goods.Beverage;
import by.home.eventOrganizer.model.goods.Goods;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.model.human.Staff;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

/**
 * The type Order.
 */
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "sequence", sequenceName = "sequence_order", initialValue = 5)
public class Order extends BaseEntity {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_goods",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private Set<Goods> goods;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_beverages",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "beverage_id")})
    private Set<Beverage> beverages;

    @ManyToMany
    @JoinTable(name = "orders_staff",
            joinColumns = {@JoinColumn(name ="order_id")},
            inverseJoinColumns = {@JoinColumn(name="staff_id")})
    private Set<Staff> staff;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String description;

    @Column(name = "sum_of_order")
    private Double price;

    @NotNull(message = "{order.executeDate.notNull}")
    @Column(name="date_of_execution", nullable = false)
    private LocalDate executeDate;

    @NotNull(message = "{order.address.notNull}")
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets staff.
     *
     * @return the staff
     */
    public Set<Staff> getStaff() {
        return staff;
    }

    /**
     * Sets staff.
     *
     * @param staff the staff
     */
    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets goods.
     *
     * @return the goods
     */
    public Set<Goods> getGoods() {
        return goods;
    }

    /**
     * Sets goods.
     *
     * @param goods the goods
     */
    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    /**
     * Gets beverages.
     *
     * @return the beverages
     */
    public Set<Beverage> getBeverages() {
        return beverages;
    }

    /**
     * Sets beverages.
     *
     * @param beverages the beverages
     */
    public void setBeverages(Set<Beverage> beverages) {
        this.beverages = beverages;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets execute date.
     *
     * @return the execute date
     */
    public LocalDate getExecuteDate() {
        return executeDate;
    }

    /**
     * Sets execute date.
     *
     * @param executeDate the execute date
     */
    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
    }

}
