package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.detail.AddressDto;
import by.home.eventOrganizer.dto.BaseDto;


public class PersonDto extends BaseDto {

    private String name;

    private String surname;

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
