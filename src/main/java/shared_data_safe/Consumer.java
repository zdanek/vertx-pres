package shared_data_safe;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.shareddata.LocalMap;
import pl.zdanek.vertx.BaseVerticle;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        getLogger().info("Consumer started! ");

        vertx.eventBus().consumer(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                getLogger().info(" Received message: " + message.body());
                getLogger().info(">>Counter " + getCounter());
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        vertx.eventBus().send(DataRepo.BUMP_COUNTER_ADDRESS, "Bump!");
    }

    private Integer getCounter() {
        LocalMap<String, Integer> map = vertx
            .sharedData().getLocalMap(Producer.SHARED_DATA_MAP);
        return map.get(Producer.COUNTER_KEY);
    }
}
