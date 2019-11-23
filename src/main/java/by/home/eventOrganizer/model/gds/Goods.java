package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods extends GdsDetail {

    @ManyToMany(mappedBy = "goods")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
