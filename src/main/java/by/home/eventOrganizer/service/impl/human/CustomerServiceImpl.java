package by.home.eventOrganizer.service.impl.human;

import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.service.human.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends PersonServiceImpl<Customer, Long> implements CustomerService {
}
