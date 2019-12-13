package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.dto.detail.AddressDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PersonDto extends BaseDto {

    @NotNull(message = "{person.name.notNull}")
    @NotEmpty(message = "{person.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{person.name.size}")
    private String name;

    private String surname;

    @NotNull(message = "{person.phoneNumber.notNull}")
    @NotEmpty(message = "{person.phoneNumber.notEmpty}")
    @Size(min = 3, max = 50, message = "{person.phoneNumber.size}")
    private Long phoneNumber;

    private AddressDto addressDto;

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
