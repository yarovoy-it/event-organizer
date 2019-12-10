package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.detail.OrderDto;
import by.home.eventOrganizer.model.detail.Order;
import by.home.eventOrganizer.service.detail.OrderService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Mapper mapper;

    private final OrderService orderService;

    public OrderController(Mapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderDto>> getAll() {
        final List<Order> staffAll = orderService.findAll();
        final List<OrderDto> staffDtoList = staffAll.stream()
                .map((staff) -> mapper.map(staff, OrderDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(staffDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> getOne(@PathVariable Long id) {
        final OrderDto staffDto = mapper.map(orderService.findById(id), OrderDto.class);
        return new ResponseEntity<>(staffDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderDto> save(@Valid @RequestBody OrderDto orderDto) {
        orderDto.setId(null);
        final OrderDto responseOrderDto = mapper.map(orderService.save(mapper.map(orderDto, Order.class)), OrderDto.class);
        return new ResponseEntity<>(responseOrderDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderDto> update(@Valid @RequestBody OrderDto orderDto, @PathVariable Long id) {
        if (!Objects.equals(id, orderDto.getId())) {
            throw new RuntimeException("controller.order.unexpectedId");
        }
        final OrderDto responseOrderDto = mapper.map(orderService.update(mapper.map(orderDto, Order.class)), OrderDto.class);
        return new ResponseEntity<>(responseOrderDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        orderService.deleteById(id);
    }
}
