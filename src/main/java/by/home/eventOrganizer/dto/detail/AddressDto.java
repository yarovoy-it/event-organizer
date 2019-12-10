package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.model.detail.enums.City;


public class AddressDto extends BaseDto {

    private City city;

    private String street;

    private Integer  houseNumber;

    private Integer apartment;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }
}
