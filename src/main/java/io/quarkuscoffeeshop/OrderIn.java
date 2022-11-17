package io.quarkuscoffeeshop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class OrderIn{
    public String orderId;
    public String itemId;

    public String name;
    public Item item;

    public Instant timeIn;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OrderIn(
            @JsonProperty("orderId") String orderId,
            @JsonProperty("itemId")String itemId,
            @JsonProperty("name")String name,
            @JsonProperty("item")Item item,
            @JsonProperty("timeIn")Instant timeIn) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.name = name;
        this.item = item;
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "OrderIn{" +
                "orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", item=" + item +
                ", timeIn=" + timeIn +
                '}';
    }
}
