package shared_data_safe;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.shareddata.LocalMap;
import pl.zdanek.vertx.BaseVerticle;

/**
 * @author bzd
 */
public class DataRepo extends BaseVerticle {

    public static final String BUMP_COUNTER_ADDRESS = "bump.address";

    @Override
    public void start() {

        vertx.eventBus().consumer(BUMP_COUNTER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message event) {
                bumpCounter();
            }
        });
    }

    private void bumpCounter() {
        LocalMap<String, Integer> map = vertx.sharedData().getLocalMap(Producer.SHARED_DATA_MAP);
        Integer counter = map.get(Producer.COUNTER_KEY);
        counter++;
        getLogger().info("Increased to [" + counter + "]");
        map.put(Producer.COUNTER_KEY, counter);
    }
}
