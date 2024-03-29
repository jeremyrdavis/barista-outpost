package io.quarkuscoffeeshop;

import jakarta.annotation.PostConstruct;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Instant;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Barista {

    private static final Logger LOGGER = LoggerFactory.getLogger(Barista.class);
    private String madeBy;

    @Inject
    @Channel("orders-out")
    Emitter<OrderUp> emitter;


    @PostConstruct
    void setHostName() {
        try {
            madeBy = InetAddress.getLocalHost().getHostName();
        } catch (IOException e) {
            LOGGER.debug("unable to get hostname");
            madeBy = "Capt. Kirk";
        }
    }

    @Incoming("barista-in")
    public void onOrderIn(final OrderIn orderIn) {
        LOGGER.debug("orderIn: {}", orderIn);
        OrderUp orderUp = make(orderIn);
        LOGGER.debug("returning: {}", orderUp);
        emitter.send(orderUp);
    }

    public OrderUp make(final OrderIn orderIn) {

        LOGGER.debug("making: {}", orderIn.item);

            sleepyTimeTime(orderIn.item);

            return new OrderUp(
                    orderIn.orderId,
                    orderIn.itemId,
                    orderIn.item,
                    orderIn.name,
                    madeBy);
    }

    private void sleepyTimeTime(final Item item) {
        int i = calculateDelay(item);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int calculateDelay(final Item item) {
        int delay;
        switch (item) {
            case COFFEE_BLACK:
                delay = 2000;
                break;
            case COFFEE_WITH_ROOM:
                delay = 2000;
                break;
            case ESPRESSO:
                delay = 2200;
                break;
            case ESPRESSO_DOUBLE:
                delay = 2200;
                break;
            case CAPPUCCINO:
                delay = 2500;
                break;
            default:
                delay = 1000;
                break;
        }
        ;
        return delay;
    }

}
