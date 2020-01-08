package by.home.eventOrganizer.dto.detail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class OrderSupplementDto {

    @NotNull(message = "{orderSupplementDto.orderId.notNull}")
    private Long orderId;

    @NotNull(message = "{orderSupplementDto.beverageNameCount.notNull}")
    @NotEmpty(message = "{orderSupplementDto.beverageNameCount.notEmpty}")
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
