package eventbus_pubsub;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;
import pl.zdanek.vertx.BaseVerticle;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    public Consumer() {
        sout("Consumer");
        sout(this.getClass().getClassLoader().toString());
    }

    @Override
    public void start() {
        getLogger().info(vertx.hashCode());
        getLogger().info("Consumer started! " + hashCode());

        vertx.eventBus().registerHandler(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {

                getLogger().info(verticleId() + " Received message: " + message.body());
            }
        });
    }

}
