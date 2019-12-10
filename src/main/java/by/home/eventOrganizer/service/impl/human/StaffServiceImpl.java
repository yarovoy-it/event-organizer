package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.repository.human.StaffRepository;
import by.home.eventOrganizer.service.human.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> findByAddress_Street(String name) {
        return staffRepository.findByAddress_Street(name);
    }

    @Override
    public List<Staff> findByPhoneNumber(Long number) {
        return staffRepository.findByPhoneNumber(number);
    }

    @Override
    public List<Staff> findByNameOrSurname(String name, String surname) {
        return staffRepository.findByNameOrSurname(name, surname);
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> findByDepartment(Department department) {
        return staffRepository.findByDepartment(department);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        return staffRepository.saveAndFlush(staff);
    }

    @Override
    public void delete(Staff staff) {
        staffRepository.delete(staff);
    }
}
