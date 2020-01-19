package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.dto.goods.BeverageDto;
import by.home.eventOrganizer.dto.goods.GoodsDto;
import by.home.eventOrganizer.dto.human.CustomerDto;
import by.home.eventOrganizer.dto.human.StaffDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * The type Order dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends BaseDto {

    private List<GoodsDto> goods;

    private List<BeverageDto> beverages;

    private Set<StaffDto> staff;

    @NotNull(message = "{order.customer.notNull}")
    private CustomerDto customer;

    private String description;

    private Double price;

    private String executeDate;

    @NotNull(message = "{order.address.notNull}")
    private AddressDto address;

    /**
     * Gets goods.
     *
     * @return the goods
     */
    public List<GoodsDto> getGoods() {
        return goods;
    }

    /**
     * Sets goods.
     *
     * @param goods the goods
     */
    public void setGoods(List<GoodsDto> goods) {
        this.goods = goods;
    }

    /**
     * Gets beverages.
     *
     * @return the beverages
     */
    public List<BeverageDto> getBeverages() {
        return beverages;
    }

    /**
     * Sets beverages.
     *
     * @param beverages the beverages
     */
    public void setBeverages(List<BeverageDto> beverages) {
        this.beverages = beverages;
    }

    /**
     * Gets staff.
     *
     * @return the staff
     */
    public Set<StaffDto> getStaff() {
        return staff;
    }

    /**
     * Sets staff.
     *
     * @param staff the staff
     */
    public void setStaff(Set<StaffDto> staff) {
        this.staff = staff;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public CustomerDto getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * Gets execute date.
     *
     * @return the execute date
     */
    public String getExecuteDate() {
        return executeDate;
    }

    /**
     * Sets execute date.
     *
     * @param executeDate the execute date
     */
    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public AddressDto getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
