package by.home.eventOrganizer.service.human.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.repository.human.StaffRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.human.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.home.eventOrganizer.component.Util.validate;

/**
 * The type Staff service.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private final LocalizedMessageSource localizedMessageSource;

    private final StaffRepository staffRepository;

    private final AddressService addressService;


    /**
     * Instantiates a new Staff service.
     *
     * @param localizedMessageSource the localized message source
     * @param staffRepository        the staff repository
     * @param addressService         the address service
     */
    public StaffServiceImpl(LocalizedMessageSource localizedMessageSource, StaffRepository staffRepository, AddressService addressService) {
        this.localizedMessageSource = localizedMessageSource;
        this.staffRepository = staffRepository;
        this.addressService = addressService;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }


    @Override
    public List<Staff> findByAddressStreet(String name) {
        validate(name == null, "error.person.address.name.null");
        List<Staff> staffList = staffRepository.findByAddressStreet(name);
        validate(staffList == null, "error.person.address.notFound");
        return staffList;
    }

    @Override
    public Optional<Staff> findByPhoneNumber(Long number) {
        validate(number == null, "error.person.number.null");
        return staffRepository.findByPhoneNumber(number);
    }

    @Override
    public List<Staff> findByNameOrSurname(String name, String surname) {
        validate(name != null && surname != null, "error.person.name.surname.null");
        List<Staff> staffList = staffRepository.findByNameOrSurname(name, surname);
        validate(staffList == null, "error.person.name.surname.notFound");
        return staffList;
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.staff.notExist", new Object[]{})));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> findByDepartment(Department department) {
        validate(department == null, "error.staff.department.null");
        List<Staff> staffList = staffRepository.findByDepartment(department);
        validate(staffList == null, "error.staff.department.notFound");
        return staffList;
    }

    @Override
    public Staff save(Staff staff) {
        validate(staff.getName() == null || staff.getPhoneNumber() == null, "error.staff.null");
        validate(findByPhoneNumber(staff.getPhoneNumber()).isPresent(), "error.person.phone.notUnique");
        if (staff.getAddress() != null) {
            addressService.save(staff.getAddress());
        }
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        final Long id = staff.getId();
        validate(id == null, "error.person.haveId");
        findByPhoneNumber(staff.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.staff.notExist", new Object[]{})));
        findById(id);
        return staffRepository.saveAndFlush(staff);
    }

    @Override
    public void delete(Staff staff) {
        validate(staff == null, "error.person.haveId");
        staffRepository.delete(staff);
    }

}
