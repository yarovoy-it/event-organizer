package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "beverages")
public class Beverage extends GdsDetail {

    @ManyToMany(mappedBy = "beverages")
    private Set<Order> orders;

    private Double volume;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
