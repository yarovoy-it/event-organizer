package by.home.eventOrganizer.dto.goods;

import by.home.eventOrganizer.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Goods detail dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsDetailDto extends BaseDto {

    @NotNull(message = "{goodsDetail.name.notNull}")
    @NotEmpty(message = "{goodsDetail.name.notEmpty}")
    private String name;

    @NotNull(message = "{goodsDetail.type.notNull}")
    @NotEmpty(message = "{goodsDetail.type.notEmpty}")
    private String type;

    private Integer count;

    private Double price;

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
}
