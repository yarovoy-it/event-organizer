package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.component.LocalizedMessageSource;
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

    private final AddressService addressService;

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(LocalizedMessageSource localizedMessageSource, AddressService addressRepository, AddressService addressService, CustomerRepository customerRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
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
    public List<Customer> findByAddress_Street(String name) {
        validate(name == null, "error.person.address.name.null");
        List<Customer> customers = customerRepository.findByAddress_Street(name);
        validate(customers == null, "error.person.address.notFound");
        return customers;
    }

    @Override
    public List<Customer> findByPhoneNumber(Long number) {
        validate(number == null, "error.person.number.null");
        List<Customer> customers = customerRepository.findByPhoneNumber(number);
        validate(customers == null, "error.person.number.notFound");
        return customers;
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
        if (customer.getAddress() != null) {
            List<Customer> staffStreet = findByAddress_Street(customer.getAddress().getStreet()).stream()
                    .filter(st -> Objects.equals(st.getAddress().getId(), customer.getAddress().getId())).collect(Collectors.toList());
            validate(staffStreet.size() == 1, "error.person.address.notUnique");
            addressService.save(customer.getAddress());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        final Long id = customer.getId();
        validate(id == null, "error.person.haveId");
        final List<Customer> duplicatePerson = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        validate(duplicatePerson.size() != 1 && !Objects.equals(duplicatePerson.get(0).getId(), id), "error.person.notUnique");
        findById(id);
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(Customer customer) {
        validate(customer == null, "error.person.haveId");
        customerRepository.delete(customer);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }

}
