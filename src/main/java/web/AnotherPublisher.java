package web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;

public class AnotherPublisher extends AbstractVerticle {

    @Override
    public void start() {
        vertx.setPeriodic(500L, new Handler<Long>() {
            @Override
            public void handle(Long event) {

                vertx.eventBus().publish("web.client", "I'm nervous type!");
            }
        });
    }
}
