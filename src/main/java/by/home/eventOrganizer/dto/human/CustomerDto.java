package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.detail.OrderDto;

import java.util.List;


public class CustomerDto extends PersonDto{

    private Integer discount;

    private Double summa;

    private List<OrderDto> orderDtoList;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }
}
