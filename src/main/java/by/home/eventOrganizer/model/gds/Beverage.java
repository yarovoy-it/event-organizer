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

    public Beverage() {

    }

    public Beverage(String name, Integer count, String type, Double price, Double volume) {
        super(name, count, type, price);
        this.volume = volume;
    }

    public Beverage(String name, Double volume, Integer count, Double price, String type) {
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public Object clone() {
        try {
            return (Beverage) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Beverage(
                    this.getName(), this.getVolume(), this.getCount(), this.getPrice(), this.getType());
        }
    }


    @Override
    public String toString() {
        return "Beverage{" +
                "volume=" + volume +
                "} " + super.toString();
    }
}
