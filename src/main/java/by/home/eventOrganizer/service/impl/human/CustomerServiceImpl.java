package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.repository.human.CustomerRepository;
import by.home.eventOrganizer.service.human.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
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
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

}
