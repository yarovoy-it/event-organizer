package by.home.eventOrganizer.model.human;

import by.home.eventOrganizer.model.base.BaseEntity;
import by.home.eventOrganizer.model.detail.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Person.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends BaseEntity {

    @Column(nullable = false)
    @NotNull(message = "{person.name.notNull}")
    @NotEmpty(message = "{person.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{person.name.size}")
    private String name;

    private String surname;

    @Column(name = "phone_number", nullable = false, unique = true)
    @NotNull(message = "{person.phoneNumber.notNull}")
    private Long phoneNumber;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", unique = true)
    private Address address;

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

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                "} " + super.toString();
    }
}
