package by.home.eventOrganizer.model.detail;

import by.home.eventOrganizer.model.base.BaseEntity;
import by.home.eventOrganizer.model.detail.enums.City;
import by.home.eventOrganizer.model.human.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Address.
 */
@Entity
@Table(name = "addresses")
@SequenceGenerator(name = "sequence", sequenceName = "sequence_address", initialValue = 110)
public class Address extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "{address.city.notNull}")
    private City city;

    @Column(nullable = false)
    @NotNull(message = "{address.street.notNull}")
    @NotEmpty(message = "{address.street.notEmpty}")
    private String street;

    @Column(name="house_number", nullable = false)
    @NotNull(message = "{address.houseNumber.notNull}")
    private Integer  houseNumber;

    private Integer apartment;

    @OneToOne(mappedBy = "address")
    private Person person;

    @OneToOne(mappedBy = "address")
    private Order order;

    /**
     * Gets order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets person.
     *
     * @param person the person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(City city) {
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
