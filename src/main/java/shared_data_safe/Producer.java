package shared_data_safe;

import eventbus_p2p.Consumer;
import org.vertx.java.core.Handler;
import pl.zdanek.vertx.BaseVerticle;

import java.util.Map;


public class Producer extends BaseVerticle {

    private static final long PERIOD_MS = 1000L;
    public static final String SHARED_DATA_MAP = "COUNTER_MAP";
    public static final String COUNTER_KEY = "COUNTER_KEY";

    private int counter = 0;

    @Override
    public void start() {

        initMap();

        getLogger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                getLogger().info(verticleId() + " Sending message " + counter);
                getLogger().info("Messages received counter from shared data" + getMesgCount());

                vertx.eventBus().publish(Consumer.CONSUMER_ADDRESS, "Message " + counter);
                counter++;
            }
        });
    }

    private void initMap() {
        Map<String, Integer> map = vertx.sharedData().getMap(Producer.SHARED_DATA_MAP);
        map.put(COUNTER_KEY, 0);
    }

    private int getMesgCount() {
        Map<String, Integer> map = vertx.sharedData().getMap(SHARED_DATA_MAP);
        return map.get(COUNTER_KEY);
    }

}
