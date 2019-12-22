package by.home.eventOrganizer.repository.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Staff repository.
 */
public interface StaffRepository extends PersonRepository<Staff, Long> {

    /**
     * Find all staff with fetch address.
     * used HQL
     *
     * @return the list of staff
     */
    @Query("SELECT st FROM Staff st JOIN FETCH st.address")
    List<Staff> findAllWithFetch();

    /**
     * Find staff by department list.
     *
     * @param department the department
     * @return the list
     */
    List<Staff> findByDepartment(Department department);

}
