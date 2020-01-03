package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.dto.gds.BeverageDto;
import by.home.eventOrganizer.dto.gds.GoodsDto;
import by.home.eventOrganizer.dto.human.CustomerDto;
import by.home.eventOrganizer.dto.human.StaffWithoutAddressDto;

import java.util.List;
import java.util.Set;


public class OrderDto extends BaseDto {

    private List<GoodsDto> goods;

    private List<BeverageDto> beverages;

    private Set<StaffWithoutAddressDto> staff;

    private CustomerDto customer;

    private String description;

    private Double price;

    private String executeDate;

    private AddressDto address;

    public List<GoodsDto> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsDto> goods) {
        this.goods = goods;
    }

    public List<BeverageDto> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<BeverageDto> beverages) {
        this.beverages = beverages;
    }

    public Set<StaffWithoutAddressDto> getStaff() {
        return staff;
    }

    public void setStaff(Set<StaffWithoutAddressDto> staff) {
        this.staff = staff;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
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
