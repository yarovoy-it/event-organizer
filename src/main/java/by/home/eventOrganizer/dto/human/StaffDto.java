package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.detail.OrderDto;
import by.home.eventOrganizer.model.human.enums.Department;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffDto extends PersonDto{

    private Department department;

    private Double salary;

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }

    private List<OrderDto> orderDtoList;

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
