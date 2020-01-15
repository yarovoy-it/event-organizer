package by.home.eventOrganizer.model.goods;

import by.home.eventOrganizer.model.detail.Order;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * The type Goods.
 */
@Entity
@Table(name = "goods")
public class Goods extends GoodsDetail {

    @ManyToMany(mappedBy = "goods")
    private Set<Order> orders;

    /**
     * Instantiates a new Goods.
     */
    public Goods() {

    }

    /**
     * Instantiates a new Goods.
     *
     * @param name  the name
     * @param count the count
     * @param type  the type
     * @param price the price
     */
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

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
