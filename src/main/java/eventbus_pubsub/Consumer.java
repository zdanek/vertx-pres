package eventbus_pubsub;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import pl.zdanek.vertx.BaseVerticle;
import pl.zdanek.vertx.LogSupport;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    public Consumer() {
        getLogger().info("Consumer");
    }

    @Override
    public void start() {
        LogSupport.logVerticle(this);
        getLogger().info("Consumer started! " + hashCode());

        vertx.eventBus().consumer(CONSUMER_ADDRESS,
            new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {

                getLogger().info("------\n" + verticleId() +
                    "\nReceived message: " + message.body() +
                    "\n");
            }
        });
    }
}
