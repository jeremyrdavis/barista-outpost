package io.quarkuscoffeeshop;

import java.time.Instant;

public record OrderUp(String orderId, String itemId, Item item, String name, String madeBy) {
}
