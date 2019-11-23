package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.repository.human.StaffRepository;
import by.home.eventOrganizer.service.human.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl extends PersonServiceImpl<Staff, Long> implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Staff> findByDepartment(Department department) {
        return staffRepository.findByDepartment(department);
    }
}
