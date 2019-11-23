package by.home.eventOrganizer.service.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;

import java.util.List;


public interface StaffService extends PersonService<Staff, Long> {

   List<Staff> findByDepartment(Department department);
}
