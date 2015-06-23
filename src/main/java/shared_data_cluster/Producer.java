package shared_data_cluster;

import io.vertx.core.shareddata.LocalMap;
import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 21.06.15.
 */
public class Producer extends BaseVerticle {


    private static final long PERIOD_MS = 1000L;
    public static final String SHARED_DATA_MAP = "COUNTER_MAP";
    public static final String COUNTER_KEY = "COUNTER_KEY";

    private int counter = 0;

    @Override
    public void start() {

        initMap();

        getLogger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, event ->

            vertx.sharedData().getClusterWideMap
                (SHARED_DATA_MAP, sharedDataEvent -> {
                    getLogger().info(
                        verticleId() + " Sending message " + counter);

                    sharedDataEvent.result().get(COUNTER_KEY,
                        result ->
                    getLogger().info(
                        "Messages received counter from shared " +
                            "data " + result.result()));

                    vertx.eventBus().publish(
                        eventbus_p2p.Consumer.CONSUMER_ADDRESS,
                        "Message " + counter);
                    counter++;
                })
        );
    }

    private void initMap() {
            vertx.sharedData().getClusterWideMap(
                Producer.SHARED_DATA_MAP,
                event -> event.result().put(COUNTER_KEY, 0, null)
            );
    }

    private int getMesgCount() {
        LocalMap<String, Integer> map = vertx.sharedData().getLocalMap(SHARED_DATA_MAP);
        return map.get(COUNTER_KEY);
    }

}
