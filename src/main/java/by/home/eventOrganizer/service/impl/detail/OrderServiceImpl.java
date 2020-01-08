package by.home.eventOrganizer.service.impl.detail;

import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.model.gds.Goods;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.repository.detail.OrderRepository;
import by.home.eventOrganizer.service.detail.OrderService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private EntityManager em;

    private Double priceOfOrderById(Long id) {
        Double orderSum;
        Double salary = Optional.ofNullable(orderRepository.getSalaryByOrderById(id)).orElse(0.0);
        Double bvPrice = Optional.ofNullable(orderRepository.getBeveragePriceByOrderById(id)).orElse(0.0);
        Double gdsPrice = Optional.ofNullable(orderRepository.getGoodsPriceByOrderById(id)).orElse(0.0);
        orderSum = salary + bvPrice + gdsPrice;
        return orderSum;
    }

    //TODO if will have time try to count order sum by criteria
    @Override
    public Order fetchesAll(Long id) {
/*
       CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        Fetch<Order, Staff> fetchSt = root.fetch("staff", JoinType.INNER);
        Fetch<Order, Beverage> fetchBv = root.fetch("beverages", JoinType.INNER);
        Fetch<Order, Goods> fetchGds = root.fetch("goods", JoinType.INNER);
        query.select(root);
        Query<Order> orderQuery = (Query<Order>) em.createQuery(query);
        orderQuery.setFirstResult(1);
        orderQuery.setMaxResults(20);
        /*
         query.select(builder.sum(root.get("salary")));

        final TypedQuery<Long> typedQuery = em.createQuery(query);
        Long sum = typedQuery.getSingleResult().longValue();

         */
        return null;

    }

    @Override
    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Order save(Order order) {
        order.setPrice(priceOfOrderById(order.getId()));
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
    public List<Order> findAllWithFetches() {
        return orderRepository.findAllWithFetches();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) {
        order.setPrice(priceOfOrderById(order.getId()));
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
