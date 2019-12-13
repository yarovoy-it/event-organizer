package by.home.eventOrganizer.model.detail;

import by.home.eventOrganizer.model.base.BaseEntity;
import by.home.eventOrganizer.model.detail.enums.City;
import by.home.eventOrganizer.model.human.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "{address.city.notNull}")
    @NotEmpty(message = "{address.city.notEmpty}")
    private City city;

    @Column(nullable = false)
    @NotNull(message = "{address.street.notNull}")
    @NotEmpty(message = "{address.street.notEmpty}")
    private String street;

    @Column(name="house_number", nullable = false)
    @NotNull(message = "{address.houseNumber.notNull}")
    @NotEmpty(message = "{address.houseNumber.notEmpty}")
    private Integer  houseNumber;

    private Integer apartment;

    @OneToOne(mappedBy = "address")
    private Person person;

    @OneToOne(mappedBy = "address")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

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

    @Override
    public String toString() {
        return "Address{" +
                "city=" + city +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", apartment=" + apartment +
                "} " + super.toString();
    }
}
