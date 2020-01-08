package by.home.eventOrganizer.model.gds;

import by.home.eventOrganizer.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@MappedSuperclass
public abstract class GdsDetail extends BaseEntity implements Cloneable {

    @Column(nullable = false)
    @NotNull(message = "{gdsDetail.name.notNull}")
    @NotEmpty(message = "{gdsDetail.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{gdsDetail.name.size}")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "{gdsDetail.count.notNull}")
    private Integer count;

    @NotNull(message = "{gdsDetail.type.notNull}")
    @NotEmpty(message = "{gdsDetail.type.notEmpty}")
    @Size(min = 3, max = 50, message = "{gdsDetail.type.size}")
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
