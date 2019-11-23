package by.home.eventOrganizer.repository.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;

import java.util.List;

/**
 * The interface Staff repository.
 */
public interface StaffRepository extends PersonRepository<Staff, Long> {


    /**
     * Find staff by department list.
     *
     * @param department the department
     * @return the list
     */
    List<Staff> findByDepartment(Department department);


}
