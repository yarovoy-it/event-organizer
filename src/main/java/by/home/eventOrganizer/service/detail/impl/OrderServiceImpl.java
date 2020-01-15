package by.home.eventOrganizer.service.detail.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.component.Util;
import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.goods.Beverage;
import by.home.eventOrganizer.model.goods.Goods;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.repository.detail.AddressRepository;
import by.home.eventOrganizer.repository.detail.OrderRepository;
import by.home.eventOrganizer.service.detail.OrderService;
import by.home.eventOrganizer.service.goods.BeverageService;
import by.home.eventOrganizer.service.goods.GoodsService;
import by.home.eventOrganizer.service.human.CustomerService;
import by.home.eventOrganizer.service.human.StaffService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final LocalizedMessageSource localizedMessageSource;

    private final Util util;

    private final OrderRepository orderRepository;

    private final BeverageService beverageService;

    private final StaffService staffService;

    private final GoodsService goodsService;

    private final CustomerService customerService;

    private final AddressRepository addressRepository;

    private final EntityManager em;


    public OrderServiceImpl(LocalizedMessageSource localizedMessageSource, Util util, OrderRepository orderRepository,
                            BeverageService beverageService, StaffService staffService, GoodsService goodsService,
                            CustomerService customerService, AddressRepository addressRepository, EntityManager em) {
        this.localizedMessageSource = localizedMessageSource;
        this.util = util;
        this.orderRepository = orderRepository;
        this.beverageService = beverageService;
        this.staffService = staffService;
        this.goodsService = goodsService;
        this.customerService = customerService;
        this.addressRepository = addressRepository;
        this.em = em;
    }

    private Double priceOfOrderById(Long id) {
        Double orderSum;
        Double salary = Optional.ofNullable(orderRepository.getSalaryByOrderById(id)).orElse(0.0);
        Double bvPrice = Optional.ofNullable(orderRepository.getBeveragePriceByOrderById(id)).orElse(0.0);
        Double gdsPrice = Optional.ofNullable(orderRepository.getGoodsPriceByOrderById(id)).orElse(0.0);
        orderSum = salary + bvPrice + gdsPrice;
        return orderSum;
    }

    @Override
    public List<Order> fetchesAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        Fetch<Order, Staff> fetchSt = root.fetch("staff", JoinType.LEFT);
        Fetch<Order, Beverage> fetchBv = root.fetch("beverages", JoinType.LEFT);
        Fetch<Order, Goods> fetchGds = root.fetch("goods", JoinType.LEFT);
        Fetch<Order, Address> fetchAre = root.fetch("address", JoinType.LEFT);
        query.select(root);
        Query<Order> orderQuery = (Query<Order>) em.createQuery(query);
        return orderQuery.stream().collect(Collectors.toList());
    }

    @Override
    public Order fetchesById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        Fetch<Order, Staff> fetchSt = root.fetch("staff", JoinType.LEFT);
        Fetch<Order, Beverage> fetchBv = root.fetch("beverages", JoinType.LEFT);
        Fetch<Order, Goods> fetchGds = root.fetch("goods", JoinType.LEFT);
        Fetch<Order, Address> fetchAre = root.fetch("address", JoinType.LEFT);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Order> orderQuery = (Query<Order>) em.createQuery(query);
        return orderQuery
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.order.notExist", new Object[]{})));
    }

    @Override
    public boolean existsById(Long id) {
        findById(id);
        return orderRepository.existsById(id);
    }

    @Override
    public Order save(Order order) {
        util.validate(order.getCustomer() == null || order.getExecuteDate() == null || order.getAddress() == null,
                "error.address.null");
        try {
            order.setStaff(emptyIfNull(order.getStaff())
                    .stream()
                    .map(staffService::save)
                    .collect(Collectors.toSet()));
        } catch (RuntimeException exception) {
            order.setStaff(order.getStaff()
                    .stream()
                    .map(staff -> staffService.findById(staff.getId()))
                    .collect(Collectors.toSet()));
        }
        order.setBeverages(emptyIfNull(order.getBeverages()).stream().map(beverageService::save).collect(Collectors.toSet()));
        order.setGoods(emptyIfNull(order.getGoods()).stream().map(goodsService::save).collect(Collectors.toSet()));
        try {
            order.setCustomer(customerService.save(order.getCustomer()));
        } catch (RuntimeException exception) {
            order.setCustomer(customerService.findByPhoneNumber(order.getCustomer().getPhoneNumber()).get(0));
        }
        order.setAddress(addressRepository.save(order.getAddress()));
        orderRepository.saveAndFlush(order);
        order.setPrice(priceOfOrderById(order.getId()));
        return order;
    }


    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.order.notExist", new Object[]{})));
    }

    @Override
    public Double orderStaffSalaryByCustomerPhoneNumber(Long number) {
        util.validate(number == null, "error.order.phoneNumber.null");
        return orderRepository.orderPriceByCustomerPhoneNumber(number);
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) {
        final Long id = order.getId();
        util.validate(id == null, "error.order.haveId");
        orderRepository.saveAndFlush(order);
        order.setPrice(priceOfOrderById(order.getId()));
        return order;
    }

    @Override
    public void delete(Order order) {
        util.validate(order == null, "error.order.null");
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }
}
