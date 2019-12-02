package by.home.eventOrganizer.model.human;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends Person{

    private Integer discount;

    private Double summa;

    @OneToMany(mappedBy = "customer")
    private List<Order> order;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "discount=" + discount +
                ", summa=" + summa +
//                ", order=" + order +
                "} " + super.toString();
    }
}
