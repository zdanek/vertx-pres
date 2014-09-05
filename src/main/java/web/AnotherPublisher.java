package web;

import org.vertx.java.core.Handler;
import org.vertx.java.platform.Verticle;

public class AnotherPublisher extends Verticle {

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
