package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beverages")
public class Beverage extends GdsDetail {

    @ManyToMany(mappedBy = "beverages")
    private List<Order> orders;

    private Double volume;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
