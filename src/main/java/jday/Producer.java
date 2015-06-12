package jday;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;

public class Producer extends AbstractVerticle {

    @Override
    public void start() {

        vertx.setPeriodic(2000L, new Handler<Long>() {
            @Override
            public void handle(Long event) {
                vertx.eventBus().publish("address", "Hello");
            }
        });
    }
}
