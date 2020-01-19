package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.dto.detail.AddressDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Person dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto extends BaseDto {

    @NotNull(message = "{person.name.notNull}")
    @NotEmpty(message = "{person.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{person.name.size}")
    private String name;

    private String surname;

    @NotNull(message = "{person.phoneNumber.notNull}")
    private Long phoneNumber;

    private AddressDto address;

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
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
