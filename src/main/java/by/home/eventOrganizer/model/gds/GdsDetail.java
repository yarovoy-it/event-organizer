package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.base.BaseEntity;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class GdsDetail extends BaseEntity implements Cloneable {

    private String name;

    private Integer count;

    private String type;

    private Double price;

    public GdsDetail() {

    }

    public GdsDetail(String name, Integer count, String type, Double price) {
        this.name = name;
        this.count = count;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

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
