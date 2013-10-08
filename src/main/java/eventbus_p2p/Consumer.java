package eventbus_p2p;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class Consumer extends Verticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        getContainer().logger().info("Consumer started!");

        vertx.eventBus().registerHandler(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                // Reply to it
                getContainer().logger().info("Received message: " + message.body());
                message.reply("pong!");
            }
        });
    }
}
