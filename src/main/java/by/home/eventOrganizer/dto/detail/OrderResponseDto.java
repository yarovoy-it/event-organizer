package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.human.CustomerResponseExcludeAddressDto;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The type Order response dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {

    private CustomerResponseExcludeAddressDto customer;

    private String description;

    private Double price;

    private String executeDate;

    private AddressDto address;

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public CustomerResponseExcludeAddressDto getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(CustomerResponseExcludeAddressDto customer) {
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
