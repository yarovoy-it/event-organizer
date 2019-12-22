package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.repository.human.CustomerRepository;
import by.home.eventOrganizer.service.human.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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
        return customerRepository.findByAddress_Street(name);
    }

    @Override
    public List<Customer> findByPhoneNumber(Long number) {
        return customerRepository.findByPhoneNumber(number);
    }

    @Override
    public List<Customer> findByNameOrSurname(String name, String surname) {
        return customerRepository.findByNameOrSurname(name, surname);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("problem with customer"));
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

}
