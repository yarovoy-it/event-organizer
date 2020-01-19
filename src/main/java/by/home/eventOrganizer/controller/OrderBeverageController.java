package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.detail.OrderResponseDto;
import by.home.eventOrganizer.dto.detail.OrderSupplementDto;
import by.home.eventOrganizer.service.detail.OrderService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Order beverage controller.
 */
@RestController
@RequestMapping("/orders-beverage")
public class OrderBeverageController {

    private final Mapper mapper;

    private final OrderService orderService;

    /**
     * Instantiates a new Order beverage controller.
     *
     * @param mapper       the mapper
     * @param orderService the order service
     */
    public OrderBeverageController(Mapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    /**
     * Update response entity.
     *
     * @param orderDto the order dto
     * @return the response entity
     */
    @PutMapping
    public ResponseEntity<OrderResponseDto> update(@Valid @RequestBody OrderSupplementDto orderDto) {
        final OrderResponseDto responseOrderDto = mapper.map(orderService.updateOrderBeverages(orderDto.getOrderId(), orderDto.getNameCount()), OrderResponseDto.class);
        return new ResponseEntity<>(responseOrderDto, HttpStatus.OK);
    }
}
