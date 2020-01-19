package by.home.eventOrganizer.dto.human;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The type Customer response exclude address dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseExcludeAddressDto {

    private String name;

    private String surname;

    private Long phoneNumber;

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
