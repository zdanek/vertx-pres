package shared_data_safe;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.LoggerFactory;
import pl.zdanek.vertx.BaseVerticle;

import java.util.Map;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        LoggerFactory.getLogger(getClass()).info("Consumer started! ");

        vertx.eventBus().consumer(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                LoggerFactory.getLogger(getClass()).info(" Received message: " + message.body());
                LoggerFactory.getLogger(getClass()).info(">>Counter " + getCounter());
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        vertx.eventBus().send(DataRepo.BUMP_COUNTER_ADDRESS, "Bump!");
    }

    private Integer getCounter() {
        Map<String, Integer> map = (Map<String, Integer>) vertx.sharedData().getLocalMap(Producer.SHARED_DATA_MAP);
        return map.get(Producer.COUNTER_KEY);
    }
}
