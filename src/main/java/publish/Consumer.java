package publish;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.LoggerFactory;

public class Consumer extends AbstractVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        LoggerFactory.getLogger(getClass()).info("Consumer started! ");

        vertx.eventBus().consumer(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                LoggerFactory.getLogger(getClass()).info(" Received message: " + message.body());
            }
        });
    }

}
