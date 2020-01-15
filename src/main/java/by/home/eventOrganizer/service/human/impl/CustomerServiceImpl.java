package by.home.eventOrganizer.service.human.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.component.Util;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.repository.human.CustomerRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.human.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final LocalizedMessageSource localizedMessageSource;

    private final AddressService addressRepository;

    private final CustomerRepository customerRepository;

    private final Util util;

    public CustomerServiceImpl(LocalizedMessageSource localizedMessageSource, AddressService addressRepository, CustomerRepository customerRepository, Util util) {
        this.localizedMessageSource = localizedMessageSource;
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.util = util;
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
        util.validate(name == null, "error.person.address.name.null");
        List<Customer> customers = customerRepository.findByAddressStreet(name);
        util.validate(customers == null, "error.person.address.notFound");
        return customers;
    }

    @Override
    public List<Customer> findByPhoneNumber(Long number) {
        util.validate(number == null, "error.person.number.null");
        List<Customer> customers = customerRepository.findByPhoneNumber(number);
        util.validate(customers == null, "error.person.number.notFound");
        return customers;
    }

    @Override
    public List<Customer> findByNameOrSurname(String name, String surname) {
        util.validate(name != null && surname != null, "error.person.name.surname.null");
        List<Customer> customers = customerRepository.findByNameOrSurname(name, surname);
        util.validate(customers == null, "error.person.name.surname.notFound");
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
        util.validate(customer.getName() == null || customer.getPhoneNumber() == null, "error.customer.null");
        util.validate(util.uniqueNumber(customer), "error.person.phone.notUnique");
        if (customer.getAddress() != null) {
            List<Customer> staffStreet = findByAddressStreet(customer.getAddress().getStreet()).stream()
                    .filter(st -> Objects.equals(st.getAddress().getId(), customer.getAddress().getId())).collect(Collectors.toList());
            util.validate(staffStreet.size() == 1, "error.person.address.notUnique");
            addressRepository.save(customer.getAddress());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        final Long id = customer.getId();
        util.validate(id == null, "error.person.haveId");
        final List<Customer> duplicatePerson = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        util.validate(duplicatePerson.size() < 1, "error.person.notUnique");
        findById(id);
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(Customer customer) {
        util.validate(customer == null, "error.person.haveId");
        customerRepository.delete(customer);
    }


}
