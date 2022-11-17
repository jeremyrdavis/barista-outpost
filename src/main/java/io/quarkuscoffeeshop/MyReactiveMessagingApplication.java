package io.quarkuscoffeeshop;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.reactive.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.stream.Stream;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyReactiveMessagingApplication.class);

    @Inject
    Barista barista;

    @Inject
    @Channel("words-out")
    Emitter<String> emitter;

    /**
     * Sends message to the "words-out" channel, can be used from a JAX-RS resource or any bean of your application.
     * Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        Stream.of("Hello", "with", "SmallRye", "reactive", "message").forEach(string -> emitter.send(string));
    }

    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to the uppercase channel.
     * Messages come from the broker.
     **/
    @Incoming("words-in")
    @Transactional
    public void toUpperCase(final OrderIn orderIn) {
        LOGGER.debug("orderIn: {}", orderIn.toString());
        barista.onOrderIn(orderIn);
//        return message.withPayload(message.getPayload().toUpperCase());
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("uppercase")
    public void sink(String word) {
        System.out.println(">> " + word);
    }
}
