package by.home.eventOrganizer.service.impl.detail;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.model.gds.Goods;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.repository.detail.AddressRepository;
import by.home.eventOrganizer.repository.detail.OrderRepository;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.detail.OrderService;
import by.home.eventOrganizer.service.gds.BeverageService;
import by.home.eventOrganizer.service.gds.GoodsService;
import by.home.eventOrganizer.service.human.CustomerService;
import by.home.eventOrganizer.service.human.StaffService;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    private final AddressRepository addressRepository;

    public OrderServiceImpl(LocalizedMessageSource localizedMessageSource, OrderRepository orderRepository,
                            BeverageService beverageService, StaffService staffService, GoodsService goodsService,
                            CustomerService customerService, AddressService addressService, AddressRepository addressRepository, EntityManager em) {
        this.localizedMessageSource = localizedMessageSource;
        this.orderRepository = orderRepository;
        this.beverageService = beverageService;
        this.staffService = staffService;
        this.goodsService = goodsService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
        this.em = em;
    }


    private final EntityManager em;

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
    public boolean existsById(Long id) {
        findById(id);
        return orderRepository.existsById(id);
    }

    @Override
    public Order save(Order order) {
        validate(order.getCustomer() == null || order.getExecuteDate() == null || order.getAddress() == null,
                "error.address.null");
        Set<Goods> goods = new HashSet<>();
        Set<Beverage> beverages = new HashSet<>();
        ;
        Set<Staff> staff = new HashSet<>();
        ;
        order.getBeverages().forEach(beverage -> beverages.add(beverageService.save(beverage)));
        order.getStaff().forEach(staff1 -> staff.add(staffService.save(staff1)));
        order.getGoods().forEach(goods1 -> goods.add(goodsService.save(goods1)));
        order.setGoods(goods);
        order.setBeverages(beverages);
        order.setStaff(staff);
        order.setCustomer(customerService.save(order.getCustomer()));
        order.setAddress(addressRepository.save(order.getAddress()));
//        if(order.getCustomer().getAddress() != null) {
//            Address address = addressRepository.findById(order.getCustomer().getAddress().getId()).orElse(null);
//            if (address != null) {
//                order.getAddress().setId(null);
//                addressService.save(order.getAddress());
//            }
//        }
//        addressRepository.saveAndFlush(order.getCustomer().getAddress());
//        order.getAddress().setId(null);
//        addressRepository.saveAndFlush(order.getAddress());
//        customerRepository.saveAndFlush(order.getCustomer());
//        order.setPrice(priceOfOrderById(order.getId()));
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
    public List<Order> findAllWithFetches() {
        return orderRepository.findAllWithFetches();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) {
        final Long id = order.getId();
        validate(id == null, "error.order.haveId");
        order.setPrice(priceOfOrderById(order.getId()));
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

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
