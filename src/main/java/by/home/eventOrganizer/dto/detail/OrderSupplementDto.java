package by.home.eventOrganizer.dto.detail;

import java.util.HashMap;
import java.util.Map;

public class OrderSupplementDto {

    private Long orderId;

    private Map<String, Integer> beverageNameCount = new HashMap<>();

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Map<String, Integer> getBeverageNameCount() {
        return beverageNameCount;
    }

    public void setBeverageNameCount(Map<String, Integer> beverageNameCount) {
        this.beverageNameCount = beverageNameCount;
    }
}
