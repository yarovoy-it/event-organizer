package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.human.CustomerResponseExcludeAddressDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {

    private CustomerResponseExcludeAddressDto customer;

    private String description;

    private Double price;

    private String executeDate;

    private AddressDto address;

    public CustomerResponseExcludeAddressDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseExcludeAddressDto customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
