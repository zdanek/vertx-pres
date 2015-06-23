package web;

import io.vertx.core.AbstractVerticle;

public class NervousProducer extends AbstractVerticle {

    @Override
    public void start() {
        vertx.setPeriodic(500L, event ->
            vertx.eventBus().publish(
                "web.client",
                "I'm nervous type!"));
    }
}
