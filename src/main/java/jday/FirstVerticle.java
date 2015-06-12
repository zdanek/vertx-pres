package jday;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.LoggerFactory;

public class FirstVerticle extends AbstractVerticle {

    @Override
    public void start() {
        LoggerFactory.getLogger(getClass()).info("Hello JDay!");

        vertx.eventBus().consumer("address", new Handler<Message<String>>() {
            @Override
            public void handle(Message event) {
                LoggerFactory.getLogger(getClass()).info("I've got event: " + event.body());
            }
        });
    }
}
