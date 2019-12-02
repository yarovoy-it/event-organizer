package by.home.eventOrganizer.service.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;

import java.util.List;
import java.util.Optional;


/**
 * The interface Staff service.
 */
public interface StaffService {


    /**
     * Find staff by address street list.
     *
     * @param name the name
     * @return the list
     */
    List<Staff> findByAddress_Street(String name);

    /**
     * Find staff by phone number list.
     *
     * @param number the number
     * @return the list
     */
    List<Staff> findByPhoneNumber(Long number);

    /**
     * Find staff by name or surname list.
     *
     * @param name    the name
     * @param surname the surname
     * @return the list
     */
    List<Staff> findByNameOrSurname(String name, String surname);

    /**
     * Find staff by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Staff> findById(Long id);

    /**
     * Delete staff by id.
     *
     * @param id the id
     */
    void deleteById(Long id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Staff> findAll();

    /**
     * Find staff by department list.
     *
     * @param department the department
     * @return the list
     */
    List<Staff> findByDepartment(Department department);
}
