package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "goods")
public class Goods extends GdsDetail {

    @ManyToMany(mappedBy = "goods")
    private Set<Order> orders;

    public Goods() {

    }

    public Goods(String name, Integer count, String type, Double price) {
        super(name, count, type, price);

    }

    @Override
    public Object clone() {
        try {
            return (Goods) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Goods(this.getName(), this.getCount(), this.getType(), this.getPrice());
        }
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
