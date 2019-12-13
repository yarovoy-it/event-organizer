package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.human.CustomerDto;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.service.human.CustomerService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final Mapper mapper;

    private final CustomerService customerService;

    public CustomerController(Mapper mapper, CustomerService customerService) {
        this.mapper = mapper;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        final List<Customer> goodsAll = customerService.findAll();
        final List<CustomerDto> customerDtoList = goodsAll.stream()
                .map((goods) -> mapper.map(goods, CustomerDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> getOne(@PathVariable Long id) {
        final CustomerDto customerDto = mapper.map(customerService.findById(id), CustomerDto.class);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody CustomerDto customerDto) {
        customerDto.setId(null);
        final CustomerDto responseCustomerDto = mapper.map(customerService.save(mapper.map(customerDto , Customer.class)), CustomerDto.class);
        return new ResponseEntity<>(responseCustomerDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customerDto, @PathVariable Long id) {
        if (!Objects.equals(id, customerDto.getId())) {
            throw new RuntimeException("controller.customer.unexpectedId");
        }
        final CustomerDto responseCustomerDto = mapper.map(customerService.update(mapper.map(customerDto, Customer.class)), CustomerDto.class);
        return new ResponseEntity<>(responseCustomerDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        customerService.deleteById(id);
    }


}
