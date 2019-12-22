package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.human.StaffDto;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.human.StaffService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/staff")
public class StaffController {

    private final Mapper mapper;

    private final StaffService staffService;

    private final AddressService addressService;

    public StaffController(Mapper mapper, StaffService staffService, AddressService addressService) {
        this.mapper = mapper;
        this.staffService = staffService;
        this.addressService = addressService;
    }

    //TODO
    @GetMapping(value = "/dep/{department}")
    public ResponseEntity<List<StaffDto>> getByName(@PathVariable String department) {
        Department departEnum = Department.valueOf(department.toUpperCase());
        final List<Staff> staffDepartment = staffService.findByDepartment(departEnum);
        final List<StaffDto> staffDtoList = staffDepartment.stream()
                .map((staff) -> mapper.map(staff, StaffDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(staffDtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAll() {
        final List<Staff> staffAll = staffService.findAllWithFetch();
        final List<StaffDto> staffDtoList = staffAll.stream()
                .map((staff) -> mapper.map(staff, StaffDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(staffDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StaffDto> getOne(@PathVariable Long id) {
        final StaffDto staffDto = mapper.map(staffService.findById(id), StaffDto.class);
        return new ResponseEntity<>(staffDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StaffDto> save(@Valid @RequestBody StaffDto staffDto) {
        staffDto.setId(null);
        final StaffDto responseStaffDto = mapper.map(staffService.save(mapper.map(staffDto, Staff.class)), StaffDto.class);
        return new ResponseEntity<>(responseStaffDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StaffDto> update(@Valid @RequestBody StaffDto staffDto, @PathVariable Long id) {
        if (!Objects.equals(id, staffDto.getId())) {
            throw new RuntimeException("controller.staff.unexpectedId");
        }
        final StaffDto responseStaffDto = mapper.map(staffService.update(mapper.map(staffDto, Staff.class)), StaffDto.class);
        return new ResponseEntity<>(responseStaffDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        staffService.deleteById(id);
    }
}
