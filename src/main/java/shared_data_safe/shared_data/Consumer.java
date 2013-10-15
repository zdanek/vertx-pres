package shared_data_safe.shared_data;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import pl.zdanek.vertx.BaseVerticle;

import java.util.Map;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        getContainer().logger().info("Consumer started! ");

        vertx.eventBus().registerHandler(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                getContainer().logger().info(" Received message: " + message.body());
                getContainer().logger().info("Bumping counter");
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        Map<String, Integer> map = vertx.sharedData().getMap(Producer.SHARED_DATA_MAP);
        int counter = map.get(Producer.COUNTER_KEY);
        counter++;
        getLogger().info("Increased to [" + counter + "]");
        map.put(Producer.COUNTER_KEY, counter);
    }
}
