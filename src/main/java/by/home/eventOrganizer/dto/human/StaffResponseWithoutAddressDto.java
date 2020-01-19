package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.model.human.enums.Department;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The type Staff response without address dto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffResponseWithoutAddressDto extends BaseDto {

    private String name;

    private String surname;

    private Long phoneNumber;

    private Department department;

    private Double salary;

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
     * Gets department.
     *
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets department.
     *
     * @param department the department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Gets salary.
     *
     * @return the salary
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary the salary
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
