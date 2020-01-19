package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.human.CustomerDto;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.service.human.CustomerService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final Mapper mapper;

    private final CustomerService customerService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param mapper          the mapper
     * @param customerService the customer service
     */
    public CustomerController(Mapper mapper, CustomerService customerService) {
        this.mapper = mapper;
        this.customerService = customerService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        final List<Customer> goodsAll = customerService.findAllWithFetch();
        final List<CustomerDto> customerDtoList = goodsAll.stream()
                .map((goods) -> mapper.map(goods, CustomerDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    /**
     * Gets by phone.
     *
     * @param number the number
     * @return the by phone
     */
    @GetMapping(value = "/phone/{number}")
    public ResponseEntity<CustomerDto> getByPhone(@PathVariable Long number) {
        Customer customer = null;
        if (customerService.findByPhoneNumber(number).isPresent())
            customer = customerService.findByPhoneNumber(number).get();
        final CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> getOne(@PathVariable Long id) {
        final CustomerDto customerDto = mapper.map(customerService.findById(id), CustomerDto.class);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    /**
     * Save response entity.
     *
     * @param customerDto the customer dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody @NotNull CustomerDto customerDto) {
        customerDto.setId(null);
        Customer customer = mapper.map(customerDto, Customer.class);
        final CustomerDto responseCustomerDto = mapper.map(customerService.save(mapper.map(customerDto , Customer.class)), CustomerDto.class);
        return new ResponseEntity<>(responseCustomerDto, HttpStatus.OK);
    }

    /**
     * Update response entity.
     *
     * @param customerDto the customer dto
     * @param id          the id
     * @return the response entity
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto customerDto, @PathVariable Long id) {
        if (!Objects.equals(id, customerDto.getId())) {
            throw new RuntimeException("controller.customer.unexpectedId");
        }
        final CustomerDto responseCustomerDto = mapper.map(customerService.update(mapper.map(customerDto, Customer.class)), CustomerDto.class);
        return new ResponseEntity<>(responseCustomerDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        customerService.deleteById(id);
    }


}
