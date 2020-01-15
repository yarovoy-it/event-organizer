package by.home.eventOrganizer.model.goods;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * The type Beverage.
 */
@Entity
@Table(name = "beverages")
public class Beverage extends GoodsDetail {

    @ManyToMany(mappedBy = "beverages")
    private Set<Order> orders;

    private Double volume;

    /**
     * Instantiates a new Beverage.
     */
    public Beverage() {

    }

    /**
     * Instantiates a new Beverage.
     *
     * @param name   the name
     * @param count  the count
     * @param type   the type
     * @param price  the price
     * @param volume the volume
     */
    public Beverage(String name, Integer count, String type, Double price, Double volume) {
        super(name, count, type, price);
        this.volume = volume;
    }

    /**
     * Instantiates a new Beverage.
     *
     * @param name   the name
     * @param volume the volume
     * @param count  the count
     * @param price  the price
     * @param type   the type
     */
    public Beverage(String name, Double volume, Integer count, Double price, String type) {
    }

    /**
     * Gets volume.
     *
     * @return the volume
     */
    public Double getVolume() {
        return volume;
    }

    /**
     * Sets volume.
     *
     * @param volume the volume
     */
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
