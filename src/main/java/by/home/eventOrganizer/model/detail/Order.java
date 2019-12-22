package by.home.eventOrganizer.model.detail;

import by.home.eventOrganizer.model.base.BaseEntity;
import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.model.gds.Goods;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.model.human.Staff;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToMany
    @JoinTable(name = "orders_goods",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private Set<Goods> goods;

    @ManyToMany
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
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String description;

    @Column(name = "sum_of_order")
    private Double price;

    @Column(name="date_of_execution", nullable = false)
    private LocalDate executeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    public Set<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(Set<Beverage> beverages) {
        this.beverages = beverages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "goods=" + goods +
                ", beverages=" + beverages +
                ", staff=" + staff +
                ", customer=" + customer +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", executeDate=" + executeDate +
                ", address=" + address +
                "} " + super.toString();
    }
}
