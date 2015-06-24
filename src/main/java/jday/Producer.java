package jday;

import io.vertx.core.AbstractVerticle;

public class Producer extends AbstractVerticle {

    @Override
    public void start() {

        vertx.setPeriodic(2000L,
            event -> vertx.eventBus().publish("address", "Hello"));
    }
}
