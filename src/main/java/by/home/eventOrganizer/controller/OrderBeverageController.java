package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.detail.OrderPriceCustomerDescriptionDto;
import by.home.eventOrganizer.dto.detail.OrderSupplementDto;
import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.model.gds.Beverage;
import by.home.eventOrganizer.service.detail.OrderService;
import by.home.eventOrganizer.service.gds.BeverageService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/orders-beverage")
public class OrderBeverageController {

    private final Mapper mapper;

    private final OrderService orderService;

    private final BeverageService beverageService;

    public OrderBeverageController(Mapper mapper, OrderService orderService, BeverageService beverageService) {
        this.mapper = mapper;
        this.orderService = orderService;
        this.beverageService = beverageService;
    }

    @PutMapping
    public ResponseEntity<OrderPriceCustomerDescriptionDto> update(@Valid @RequestBody OrderSupplementDto orderDto) {
        if (orderService.existsById(orderDto.getOrderId())) {
            Order order = orderService.findById(orderDto.getOrderId());
            Set<Beverage> beverages = new HashSet<>();
            for (Map.Entry<String, Integer> entry : orderDto.getBeverageNameCount().entrySet()) {
                Beverage beverage = (Beverage) beverageService.findByName(entry.getKey()).stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("dont find beverage"))
                        .clone();
                beverage.setId(null);
                beverage.setCount(entry.getValue());
                beverages.add(beverage);
            }
            order.setBeverages(beverages);
            final OrderPriceCustomerDescriptionDto responseOrderDto = mapper.map(orderService.update(order), OrderPriceCustomerDescriptionDto.class);
            return new ResponseEntity<>(responseOrderDto, HttpStatus.OK);
        } else {
            throw new RuntimeException("we cant find this order");
        }
    }

}
