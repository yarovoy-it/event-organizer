package by.home.eventOrganizer.service.detail.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.goods.Beverage;
import by.home.eventOrganizer.model.goods.Goods;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.repository.detail.OrderRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.detail.OrderService;
import by.home.eventOrganizer.service.goods.BeverageService;
import by.home.eventOrganizer.service.goods.GoodsService;
import by.home.eventOrganizer.service.human.CustomerService;
import by.home.eventOrganizer.service.human.StaffService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

import static by.home.eventOrganizer.component.Util.validate;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

/**
 * The type Order service.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final LocalizedMessageSource localizedMessageSource;

    private final OrderRepository orderRepository;

    private final BeverageService beverageService;

    private final StaffService staffService;

    private final GoodsService goodsService;

    private final CustomerService customerService;

    private final AddressService addressService;

    private final EntityManager em;


    /**
     * Instantiates a new Order service.
     *
     * @param localizedMessageSource the localized message source
     * @param orderRepository        the order repository
     * @param beverageService        the beverage service
     * @param staffService           the staff service
     * @param goodsService           the goods service
     * @param customerService        the customer service
     * @param addressService         the address service
     * @param em                     the em
     */
    public OrderServiceImpl(LocalizedMessageSource localizedMessageSource, OrderRepository orderRepository,
                            BeverageService beverageService, StaffService staffService, GoodsService goodsService,
                            CustomerService customerService, AddressService addressService, EntityManager em) {
        this.localizedMessageSource = localizedMessageSource;
        this.orderRepository = orderRepository;
        this.beverageService = beverageService;
        this.staffService = staffService;
        this.goodsService = goodsService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.em = em;
    }

    private Double priceOfOrder(Set<Beverage> beverages, Set<Goods> goods, Set<Staff> staff) {
        Double orderSum;
        Double staffPrice = Optional.of(staff.stream()
                .map(Staff::getSalary)
                .mapToDouble(Double::doubleValue)
                .sum()).orElse(0.0);
        Double goodsPrice = Optional.of(goods.stream()
                .map((streamGoods) -> streamGoods.getCount() * streamGoods.getPrice())
                .mapToDouble(Double::doubleValue)
                .sum()).orElse(0.0);
        Double beveragePrice = Optional.of(beverages.stream()
                .map((bv) -> bv.getCount() * bv.getPrice())
                .mapToDouble(Double::doubleValue)
                .sum()).orElse(0.0);
        orderSum = staffPrice + goodsPrice + beveragePrice;
        return orderSum;
    }

    @Override
    public List<Order> fetchesAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        root.fetch("staff", JoinType.LEFT);
        root.fetch("beverages", JoinType.LEFT);
        root.fetch("goods", JoinType.LEFT);
        root.fetch("address", JoinType.LEFT);
        query.select(root);
        Query<Order> orderQuery = (Query<Order>) em.createQuery(query);
        return orderQuery.stream().collect(Collectors.toList());
    }


    @Override
    public Order updateOrderGoods(Long orderId, Map<String, Integer> mapGoods) {
        Order order = findById(orderId);
        final Set<Goods> transientGoods = new HashSet<>();
        for (Map.Entry<String, Integer> entry : mapGoods.entrySet()) {
            if (order.getGoods().stream().noneMatch(goods -> goods.getName().equals(entry.getKey()))) {
                Goods goods = (Goods) goodsService.findByName(entry.getKey()).stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("error.goods.notExist"))
                        .clone();
                goods.setId(null);
                goods.setCount(entry.getValue());
                transientGoods.add(goods);
            } else {
                Goods persistGoods = order.getGoods()
                        .stream()
                        .filter(goods -> entry.getKey().equals(goods.getName()))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("error.goods.notExist"));
                persistGoods.setCount(entry.getValue());
                transientGoods.add(persistGoods);
            }
        }
        order.setGoods(transientGoods);
        return update(order);
    }

    @Override
    public Order updateOrderBeverages(Long orderId, Map<String, Integer> mapBeverages) {
        Order order = findById(orderId);
        Set<Beverage> transientBeverages = new HashSet<>();
        for (Map.Entry<String, Integer> entry : mapBeverages.entrySet()) {
            if (order.getBeverages().stream().noneMatch(bv -> bv.getName().equals(entry.getKey()))) {
                Beverage beverage = (Beverage) beverageService.findByName(entry.getKey()).stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("error.beverage.notExist"))
                        .clone();
                beverage.setId(null);
                beverage.setCount(entry.getValue());
                transientBeverages.add(beverage);
            } else {
                Beverage persistBeverage = order.getBeverages()
                        .stream()
                        .filter(bv -> entry.getKey().equals(bv.getName()))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("error.beverage.notExist"));
                persistBeverage.setCount(entry.getValue());
                transientBeverages.add(persistBeverage);
            }
        }
        order.setBeverages(transientBeverages);
        return update(order);
    }

    @Override
    public Order fetchesById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        root.fetch("staff", JoinType.LEFT);
        root.fetch("beverages", JoinType.LEFT);
        root.fetch("goods", JoinType.LEFT);
        root.fetch("address", JoinType.LEFT);
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
        validate(order.getCustomer() == null || order.getExecuteDate() == null || order.getAddress() == null,
                "error.address.null");
        order.setStaff(emptyIfNull(order.getStaff())
                .stream()
                .filter(staff -> staffService.findByPhoneNumber(staff.getPhoneNumber()).isPresent())
                .map(staff -> staffService.findByPhoneNumber(staff.getPhoneNumber())
                        .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.staff.notExist", new Object[]{}))))
                .collect(Collectors.toSet()));
        order.setBeverages(emptyIfNull(order.getBeverages())
                .stream()
                .map(beverageService::save)
                .collect(Collectors.toSet()));
        order.setGoods(emptyIfNull(order.getGoods())
                .stream()
                .map(goodsService::save)
                .collect(Collectors.toSet()));
        order.setCustomer(customerService.findByPhoneNumber(order.getCustomer().getPhoneNumber())
                .orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.customer.notExist", new Object[]{}))));
        order.setAddress(addressService.save(order.getAddress()));
        order.setPrice(priceOfOrder(order.getBeverages(), order.getGoods(), order.getStaff()));
        return orderRepository.saveAndFlush(order);
    }


    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.order.notExist", new Object[]{})));
    }

    @Override
    public Double orderStaffSalaryByCustomerPhoneNumber(Long number) {
        validate(number == null, "error.order.phoneNumber.null");
        return orderRepository.orderPriceByCustomerPhoneNumber(number);
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) {
        final Long id = order.getId();
        validate(id == null, "error.order.haveId");
        order.setPrice(priceOfOrder(order.getBeverages(), order.getGoods(), order.getStaff()));
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(Order order) {
        validate(order == null, "error.order.null");
        orderRepository.delete(order);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }
}
