package by.home.eventOrganizer.service.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;

import java.util.List;


/**
 * The interface Staff service.
 */
public interface StaffService {


    /**
     * Find by address street list.
     *
     * @param name the name
     * @return the list
     */
    List<Staff> findByAddress_Street(String name);

    /**
     * Find by phone number list.
     *
     * @param number the number
     * @return the list
     */
    List<Staff> findByPhoneNumber(Long number);

    /**
     * Find by name or surname list.
     *
     * @param name    the name
     * @param surname the surname
     * @return the list
     */
    List<Staff> findByNameOrSurname(String name, String surname);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Staff findById(Long id);

    /**
     * Delete by id.
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
     * Find by department list.
     *
     * @param department the department
     * @return the list
     */
    List<Staff> findByDepartment(Department department);

    /**
     * Save staff.
     *
     * @param staff the staff
     * @return the staff
     */
    Staff save(Staff staff);

    /**
     * Update staff.
     *
     * @param staff the staff
     * @return the staff
     */
    Staff update(Staff staff);

    /**
     * Delete.
     *
     * @param staff the staff
     */
    void delete(Staff staff);

}
