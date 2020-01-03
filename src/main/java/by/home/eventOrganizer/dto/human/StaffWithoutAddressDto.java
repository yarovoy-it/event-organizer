package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.BaseDto;
import by.home.eventOrganizer.model.human.enums.Department;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StaffWithoutAddressDto extends BaseDto {

    @NotNull(message = "{person.name.notNull}")
    @NotEmpty(message = "{person.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{person.name.size}")
    private String name;

    private String surname;

    @NotNull(message = "{person.phoneNumber.notNull}")
    private Long phoneNumber;

    private Department department;

    private Double salary;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
