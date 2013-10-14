package shared_data_safe;

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
                getContainer().logger().info(">>Counter " + getCounter());
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        vertx.eventBus().send(DataRepo.BUMP_COUNTER_ADDRESS, "Bump!");
    }

    private Integer getCounter() {
        Map<String, Integer> map = vertx.sharedData().getMap(Producer.SHARED_DATA_MAP);
        return map.get(Producer.COUNTER_KEY);
    }
}
