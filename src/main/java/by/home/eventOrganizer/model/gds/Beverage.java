package by.home.eventOrganizer.model.gds;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "beverages")
public class Beverage extends GdsDetail {

//    @ManyToMany(mappedBy = "beverages")
//    private List<Order> orders;

    private Double volume;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }

    @Override
    public String toString() {
        return "Beverage{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
