package io.quarkuscoffeeshop;

import io.quarkus.kafka.client.runtime.ui.model.Order;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class OrderInDeserializer extends ObjectMapperDeserializer<OrderIn> {

    public OrderInDeserializer() {
        super(OrderIn.class);
    }
}
