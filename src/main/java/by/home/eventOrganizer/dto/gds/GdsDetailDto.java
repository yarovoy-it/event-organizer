package by.home.eventOrganizer.dto.gds;

import by.home.eventOrganizer.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GdsDetailDto extends BaseDto {

    @NotNull(message = "{gdsDetail.name.notNull}")
    @NotEmpty(message = "{gdsDetail.name.notEmpty}")
    private String name;

    @NotNull(message = "{gdsDetail.type.notNull}")
    @NotEmpty(message = "{gdsDetail.type.notEmpty}")
    private String type;

    private Integer count;

    private Double price;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
