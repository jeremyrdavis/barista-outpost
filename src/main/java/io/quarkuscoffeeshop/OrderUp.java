package io.quarkuscoffeeshop;

public record OrderUp(String orderId, String lineItemId, Item item, String name, String madeBy) {
}
