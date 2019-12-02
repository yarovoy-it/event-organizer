package by.home.eventOrganizer.model.human;

import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.human.enums.Department;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff extends Person{

    @Enumerated(EnumType.STRING)
    private Department department;

    @ManyToMany(mappedBy = "staff")
    private List<Order> orders;

    private Double salary;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    @Override
    public String toString() {
        return "Staff{" +
                "department=" + department +
//                ", orders=" + orders +
                ", salary=" + salary +
                "} " + super.toString();
    }
}
