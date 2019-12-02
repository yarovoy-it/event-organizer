package by.home.eventOrganizer.service.impl.detail;

import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.repository.detail.OrderRepository;
import by.home.eventOrganizer.service.detail.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveTransientOrder(Order order) {
        //TODO create price during saving new order (for transient)
        Double price = null;
        System.out.println(price = orderStaffSalaryByCustomerPhoneNumber(order.getCustomer().getPhoneNumber()));
        order.setPrice(price);
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void save(Order order) {
        orderRepository.saveAndFlush(order);
    }

    private double priceStaffGenerator(Order order) {
        Double salaryStaff = order.getPrice();
        Double price = null;
        if (salaryStaff != null) {
            salaryStaff = 0.0;
            price = order.getStaff().stream().flatMapToDouble((o) -> DoubleStream.of(o.getSalary())).sum();
        }
        price = order.getStaff().stream().flatMapToDouble((o) -> DoubleStream.of(o.getSalary())).sum();
        return salaryStaff + price;
    }

    private double priceGoodsGenerator(Order order) {
        Double goodsPrice = order.getPrice();
        Double price = null;
        if (goodsPrice == null) {
            goodsPrice = 0.0;
            price = order.getGoods().stream().flatMapToDouble((o) -> DoubleStream.of(o.getPrice())).sum();
        }
        return goodsPrice + price;
    }

    private double priceBeverageGenerator(Order order) {
        Double beveragePrice = order.getPrice();
        Double price = null;
        if (beveragePrice == null) {
            beveragePrice = 0.0;
            price = order.getBeverages().stream().flatMapToDouble((o) -> DoubleStream.of(o.getPrice())).sum();
        }
        return beveragePrice + price;
    }


    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Double orderStaffSalaryByCustomerPhoneNumber(Long number) {
        return orderRepository.orderPriceByCustomerPhoneNumber(number);
    }
}
