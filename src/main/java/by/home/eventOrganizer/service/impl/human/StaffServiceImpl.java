package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.repository.human.StaffRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.human.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private final LocalizedMessageSource localizedMessageSource;

    private final StaffRepository staffRepository;

    private final AddressService addressService;

    private final AddressService addressRepository;

    public StaffServiceImpl(LocalizedMessageSource localizedMessageSource, StaffRepository staffRepository, AddressService addressService, AddressService addressRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.staffRepository = staffRepository;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> findAllWithFetch() {
        return staffRepository.findAllWithFetch();
    }

    @Override
    public List<Staff> findByAddress_Street(String name) {
        validate(name == null, "error.person.address.name.null");
        List<Staff> staffList = staffRepository.findByAddress_Street(name);
        validate(staffList == null, "error.person.address.notFound");
        return staffList;
    }

    @Override
    public List<Staff> findByPhoneNumber(Long number) {
        validate(number == null, "error.person.number.null");
        List<Staff> staffList = staffRepository.findByPhoneNumber(number);
        validate(staffList == null, "error.person.number.notFound");
        return staffList;
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
        if (staff.getAddress() != null) {
            List<Staff> staffStreet = findByAddress_Street(staff.getAddress().getStreet()).stream()
                    .filter(st -> Objects.equals(st.getAddress().getId(), staff.getAddress().getId())).collect(Collectors.toList());
            validate(staffStreet.size() == 1, "error.person.address.notUnique");
            addressRepository.save(staff.getAddress());
        }
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        final Long id = staff.getId();
        validate(id == null, "error.person.haveId");
        final List<Staff> duplicatePerson = staffRepository.findByPhoneNumber(staff.getPhoneNumber());
        validate(duplicatePerson.size() != 1 && !Objects.equals(duplicatePerson.get(0).getId(), id), "error.person.notUnique");
        findById(id);
        return staffRepository.saveAndFlush(staff);
    }

    @Override
    public void delete(Staff staff) {
        validate(staff == null, "error.person.haveId");
        staffRepository.delete(staff);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
