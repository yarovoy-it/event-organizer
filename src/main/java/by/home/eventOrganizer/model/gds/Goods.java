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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
