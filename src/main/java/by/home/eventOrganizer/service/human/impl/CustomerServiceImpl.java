package by.home.eventOrganizer.service.human.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.repository.human.CustomerRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.human.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.home.eventOrganizer.component.Util.validate;

/**
 * The type Customer service.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final LocalizedMessageSource localizedMessageSource;

    private final AddressService addressRepository;

    private final CustomerRepository customerRepository;

    /**
     * Instantiates a new Customer service.
     *
     * @param localizedMessageSource the localized message source
     * @param addressRepository      the address repository
     * @param customerRepository     the customer repository
     */
    public CustomerServiceImpl(LocalizedMessageSource localizedMessageSource, AddressService addressRepository, CustomerRepository customerRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllWithFetch() {
        return customerRepository.findAllWithFetch();
    }

    @Override
    public List<Customer> findByAddressStreet(String name) {
        validate(name == null, "error.person.address.name.null");
        List<Customer> customers = customerRepository.findByAddressStreet(name);
        validate(customers == null, "error.person.address.notFound");
        return customers;
    }

    @Override
    public Optional<Customer> findByPhoneNumber(Long number) {
        validate(number == null, "error.person.number.null");
        return customerRepository.findByPhoneNumber(number);
    }

    @Override
    public List<Customer> findByNameOrSurname(String name, String surname) {
        validate(name != null && surname != null, "error.person.name.surname.null");
        List<Customer> customers = customerRepository.findByNameOrSurname(name, surname);
        validate(customers == null, "error.person.name.surname.notFound");
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.customer.notExist", new Object[]{})));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public Customer save(Customer customer) {
        validate(customer.getName() == null || customer.getPhoneNumber() == null, "error.customer.null");
        validate(findByPhoneNumber(customer.getPhoneNumber()).isPresent(), "error.person.phone.notUnique");
        if (customer.getAddress() != null) {
            addressRepository.save(customer.getAddress());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        final Long id = customer.getId();
        validate(id == null, "error.person.haveId");
        findByPhoneNumber(customer.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.customer.notExist", new Object[]{})));
        findById(id);
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(Customer customer) {
        validate(customer == null, "error.person.haveId");
        customerRepository.delete(customer);
    }


}
