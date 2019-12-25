package by.home.eventOrganizer.service.impl.detail;

import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.repository.detail.OrderRepository;
import by.home.eventOrganizer.service.detail.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.saveAndFlush(order);  }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("error.order.notExist"));
    }

    @Override
    public Double orderStaffSalaryByCustomerPhoneNumber(Long number) {
        return orderRepository.orderPriceByCustomerPhoneNumber(number);
    }

    @Override
    public Order findByIdWithFetches(Long id) {
        Order order = orderRepository.findByIdWithFetches(id);
        if (order.getStaff() == null) {
            order.setStaff((Set<Staff>) new Staff());
        } else if (order.getBeverages() == null) {
            order.setBeverages((Set<Beverage>) new Beverage());
        }
        return order;
    }

    @Override
    public List<Order> findAllWithFetches() {
        return orderRepository.findAllWithFetches();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
