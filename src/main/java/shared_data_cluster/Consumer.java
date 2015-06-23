package shared_data_cluster;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.LocalMap;
import pl.zdanek.vertx.BaseVerticle;
import shared_data_local.Producer;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        getLogger().info("Consumer started! ");

        vertx.eventBus().consumer(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                getLogger().info(
                    " Received message: " + message.body());
                getLogger().info("Bumping counter");
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        vertx.sharedData().getClusterWideMap(
            shared_data_local.Producer.SHARED_DATA_MAP, event -> {
                event.result().get(shared_data_local.Producer
                    .COUNTER_KEY, mapValue -> {
                    Integer val = (Integer) mapValue.result();
                    getLogger().info("Value from cluster map " +
                        "[" + val + "]");
                    val++;

                    event.result().put(
                        shared_data_cluster.Producer
                            .COUNTER_KEY, val, null);
                    getLogger().info("Increased to [" + val + "]");
                });
            });

//        map.put(shared_data_local.Producer.COUNTER_KEY, counter);
     }

    private void bumpCounterClustered() {
        vertx.sharedData().getClusterWideMap(
            shared_data_local.Producer.SHARED_DATA_MAP, new Handler<AsyncResult<AsyncMap<String, Integer>>>() {
            @Override
            public void handle(AsyncResult<AsyncMap<String, Integer>> event) {
                LocalMap <String, Integer> map = (LocalMap<String, Integer>) event.result();
                int counter = map.get(shared_data_local.Producer.COUNTER_KEY);
                counter++;
                getLogger().info("Increased to [" + counter + "]");
                map.put(Producer.COUNTER_KEY, counter);
            }
        });

    }

}
