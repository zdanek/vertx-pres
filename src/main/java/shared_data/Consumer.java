package shared_data;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.AsyncMap;
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
                LoggerFactory.getLogger(getClass()).info("Bumping counter");
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        Map<String, Integer> map = (Map<String, Integer>) vertx.sharedData().getLocalMap(Producer.SHARED_DATA_MAP);
        int counter = map.get(Producer.COUNTER_KEY);
        counter++;
        getLogger().info("Increased to [" + counter + "]");
        map.put(Producer.COUNTER_KEY, counter);
     }

    private void bumpCounterClustered() {
        vertx.sharedData().getClusterWideMap(Producer.SHARED_DATA_MAP, new Handler<AsyncResult<AsyncMap<String, Integer>>>() {
            @Override
            public void handle(AsyncResult<AsyncMap<String, Integer>> event) {
                Map <String, Integer> map = (Map<String, Integer>) event.result();
                int counter = map.get(Producer.COUNTER_KEY);
                counter++;
                getLogger().info("Increased to [" + counter + "]");
                map.put(Producer.COUNTER_KEY, counter);
            }
        });

    }

}
