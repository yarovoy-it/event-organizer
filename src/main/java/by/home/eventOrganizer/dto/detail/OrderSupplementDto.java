package by.home.eventOrganizer.dto.detail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Order supplement dto.
 */
public class OrderSupplementDto {

    @NotNull(message = "{orderSupplementDto.orderId.notNull}")
    private Long orderId;

    @NotNull(message = "{orderSupplementDto.beverageNameCount.notNull}")
    @NotEmpty(message = "{orderSupplementDto.beverageNameCount.notEmpty}")
    private Map<String, Integer> nameCount = new HashMap<>();

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets name count.
     *
     * @return the name count
     */
    public Map<String, Integer> getNameCount() {
        return nameCount;
    }

    /**
     * Sets name count.
     *
     * @param nameCount the name count
     */
    public void setNameCount(Map<String, Integer> nameCount) {
        this.nameCount = nameCount;
    }
}
