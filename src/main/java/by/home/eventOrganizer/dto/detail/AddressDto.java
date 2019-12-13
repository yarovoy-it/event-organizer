package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.model.detail.enums.City;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class AddressDto extends BaseDto {

    @NotNull(message = "{address.city.notNull}")
    @NotEmpty(message = "{address.city.notEmpty}")
    private City city;

    @NotNull(message = "{address.street.notNull}")
    @NotEmpty(message = "{address.street.notEmpty}")
    private String street;

    @NotNull(message = "{address.houseNumber.notNull}")
    @NotEmpty(message = "{address.houseNumber.notEmpty}")
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
