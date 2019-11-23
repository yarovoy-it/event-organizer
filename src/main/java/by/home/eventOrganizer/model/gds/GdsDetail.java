package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.base.BaseEntity;

import javax.persistence.*;


@MappedSuperclass
public abstract class GdsDetail extends BaseEntity {

    private String name;

    private Integer count;

    private String type;

    private Double price;

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
}
