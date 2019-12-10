package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.dto.gds.BeverageDto;
import by.home.eventOrganizer.dto.gds.GoodsDto;
import by.home.eventOrganizer.dto.human.CustomerDto;
import by.home.eventOrganizer.dto.human.StaffDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OrderDto extends BaseDto {

    private List<GoodsDto> goodsDtoList;

    private List<BeverageDto> beverageDtoList;

    private Set<StaffDto> staffDtoSet;

    private CustomerDto customerDto;

    private String description;

    private Double price;

    private LocalDate executeDate;

    private AddressDto addressDto;

    public List<GoodsDto> getGoodsDtoList() {
        return goodsDtoList;
    }

    public void setGoodsDtoList(List<GoodsDto> goodsDtoList) {
        this.goodsDtoList = goodsDtoList;
    }

    public List<BeverageDto> getBeverageDtoList() {
        return beverageDtoList;
    }

    public void setBeverageDtoList(List<BeverageDto> beverageDtoList) {
        this.beverageDtoList = beverageDtoList;
    }

    public Set<StaffDto> getStaffDtoSet() {
        return staffDtoSet;
    }

    public void setStaffDtoSet(Set<StaffDto> staffDtoSet) {
        this.staffDtoSet = staffDtoSet;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
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

    public LocalDate getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
