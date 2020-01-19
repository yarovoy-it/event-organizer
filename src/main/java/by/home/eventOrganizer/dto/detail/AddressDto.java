package by.home.eventOrganizer.dto.detail;

import by.home.eventOrganizer.dto.BaseDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * The type Address dto.
 */
public class AddressDto extends BaseDto {

    @NotNull(message = "{address.city.notNull}")
    @NotEmpty(message = "{address.city.notEmpty}")
    private String city;

    @NotNull(message = "{address.street.notNull}")
    @NotEmpty(message = "{address.street.notEmpty}")
    private String street;

    @NotNull(message = "{address.houseNumber.notNull}")
    private Integer  houseNumber;

    private Integer apartment;

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets house number.
     *
     * @return the house number
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets house number.
     *
     * @param houseNumber the house number
     */
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Gets apartment.
     *
     * @return the apartment
     */
    public Integer getApartment() {
        return apartment;
    }

    /**
     * Sets apartment.
     *
     * @param apartment the apartment
     */
    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }
}
