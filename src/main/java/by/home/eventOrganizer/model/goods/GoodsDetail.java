package by.home.eventOrganizer.model.goods;

import by.home.eventOrganizer.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The type Goods detail.
 */
@MappedSuperclass
public abstract class GoodsDetail extends BaseEntity implements Cloneable {

    @Column(nullable = false)
    @NotNull(message = "{goodsDetail.name.notNull}")
    @NotEmpty(message = "{goodsDetail.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{gdsDetail.name.size}")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "{goodsDetail.count.notNull}")
    private Integer count;

    @NotNull(message = "{goodsDetail.type.notNull}")
    @NotEmpty(message = "{goodsDetail.type.notEmpty}")
    @Size(min = 3, max = 50, message = "{goodsDetail.type.size}")
    private String type;

    private Double price;

    /**
     * Instantiates a new Goods detail.
     */
    public GoodsDetail() {

    }

    /**
     * Instantiates a new Goods detail.
     *
     * @param name  the name
     * @param count the count
     * @param type  the type
     * @param price the price
     */
    public GoodsDetail(String name, Integer count, String type, Double price) {
        this.name = name;
        this.count = count;
        this.type = type;
        this.price = price;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GdsDetail{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", type='" + type + '\'' +
                ", price=" + price +
                "} " + super.toString();
    }
}
