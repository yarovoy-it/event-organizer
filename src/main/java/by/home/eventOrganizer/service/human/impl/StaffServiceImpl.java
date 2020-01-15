package by.home.eventOrganizer.service.human.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.component.Util;
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

    private final Util util;

    public StaffServiceImpl(LocalizedMessageSource localizedMessageSource, StaffRepository staffRepository, AddressService addressService, Util util) {
        this.localizedMessageSource = localizedMessageSource;
        this.staffRepository = staffRepository;
        this.addressService = addressService;
        this.util = util;
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
    public List<Staff> findByAddressStreet(String name) {
        util.validate(name == null, "error.person.address.name.null");
        List<Staff> staffList = staffRepository.findByAddressStreet(name);
        util.validate(staffList == null, "error.person.address.notFound");
        return staffList;
    }

    @Override
    public List<Staff> findByPhoneNumber(Long number) {
        util.validate(number == null, "error.person.number.null");
        List<Staff> staffList = staffRepository.findByPhoneNumber(number);
        util.validate(staffList == null, "error.person.number.notFound");
        return staffList;
    }

    @Override
    public List<Staff> findByNameOrSurname(String name, String surname) {
        util.validate(name != null && surname != null, "error.person.name.surname.null");
        List<Staff> staffList = staffRepository.findByNameOrSurname(name, surname);
        util.validate(staffList == null, "error.person.name.surname.notFound");
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
        util.validate(department == null, "error.staff.department.null");
        List<Staff> staffList = staffRepository.findByDepartment(department);
        util.validate(staffList == null, "error.staff.department.notFound");
        return staffList;
    }

    @Override
    public Staff save(Staff staff) {
        util.validate(staff.getName() == null || staff.getPhoneNumber() == null, "error.staff.null");
        util.validate(util.uniqueNumber(staff), "error.person.phone.notUnique");
        if (staff.getAddress() != null) {
            List<Staff> staffStreet = findByAddressStreet(staff.getAddress().getStreet()).stream()
                    .filter(st -> Objects.equals(st.getAddress().getId(), staff.getAddress().getId())).collect(Collectors.toList());
            util.validate(staffStreet.size() == 1, "error.person.address.notUnique");
            addressService.save(staff.getAddress());
        }
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff) {
        final Long id = staff.getId();
        util.validate(id == null, "error.person.haveId");
        final List<Staff> duplicatePerson = staffRepository.findByPhoneNumber(staff.getPhoneNumber()).stream()
                .filter(st -> Objects.equals(st.getAddress().getId(), staff.getAddress().getId())).collect(Collectors.toList());
        util.validate(duplicatePerson.size() < 1, "error.person.notUnique");
        findById(id);
        return staffRepository.saveAndFlush(staff);
    }

    @Override
    public void delete(Staff staff) {
        util.validate(staff == null, "error.person.haveId");
        staffRepository.delete(staff);
    }

}
