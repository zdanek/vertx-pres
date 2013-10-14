package shared_data_safe;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import pl.zdanek.vertx.BaseVerticle;

import java.util.Map;

/**
 * @author bzd
 */
public class DataRepo extends BaseVerticle {

    public static final String BUMP_COUNTER_ADDRESS = "bump.address";

    @Override
    public void start() {

        vertx.eventBus().registerHandler(BUMP_COUNTER_ADDRESS, new Handler<Message>() {
            @Override
            public void handle(Message event) {
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        Map<String, Integer> map = vertx.sharedData().getMap(Producer.SHARED_DATA_MAP);
        Integer counter = map.get(Producer.COUNTER_KEY);
        counter++;
        getLogger().info("Increased to [" + counter + "]");
        map.put(Producer.COUNTER_KEY, counter);
    }
}
