package io.quarkuscoffeeshop;

public record OrderIn(String orderId, String itemId, String name, Item item) {
}
