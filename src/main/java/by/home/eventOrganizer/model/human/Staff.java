package by.home.eventOrganizer.model.human;

import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.human.enums.Department;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The type Staff.
 */
@Entity
@Table(name = "staff")
public class Staff extends Person{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{person.name.notNull}")
    private Department department;

    @ManyToMany(mappedBy = "staff")
    private List<Order> orders;

    private Double salary;

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    @Override
    public String toString() {
        return "Staff{" +
                "department=" + department +
                ", salary=" + salary +
                "} " + super.toString();
    }
}
