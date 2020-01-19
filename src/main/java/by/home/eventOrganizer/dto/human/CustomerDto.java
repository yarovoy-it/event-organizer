package by.home.eventOrganizer.dto.human;

import by.home.eventOrganizer.dto.detail.OrderDto;

import java.util.List;


/**
 * The type Customer dto.
 */
public class CustomerDto extends PersonDto{

    private Integer discount;

    private Double sum;

    private List<OrderDto> orderDtoList;

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * Gets sum.
     *
     * @return the sum
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Sets sum.
     *
     * @param sum the sum
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * Gets order dto list.
     *
     * @return the order dto list
     */
    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    /**
     * Sets order dto list.
     *
     * @param orderDtoList the order dto list
     */
    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }
}
