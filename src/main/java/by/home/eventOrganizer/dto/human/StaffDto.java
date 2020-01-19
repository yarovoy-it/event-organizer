package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.detail.OrderDto;
import by.home.eventOrganizer.model.human.enums.Department;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The type Staff dto.
 */
public class StaffDto extends PersonDto{

    @NotNull(message = "{staff.department.notNull}")
    private Department department;

    private Double salary;

    /**
     * Gets order dto list.
     *
     * @return the order dto list
     */
    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    /**
     * Sets order dto list.
     *
     * @param orderDtoList the order dto list
     */
    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }

    private List<OrderDto> orderDtoList;

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
