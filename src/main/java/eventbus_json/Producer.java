package eventbus_json;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.impl.Json;
import pl.zdanek.vertx.BaseVerticle;


public class Producer extends BaseVerticle {

    private static final long PERIOD_MS = 1000L;

    private int counter = 0;

    public Producer() {
        sout("Producer");
        sout(this.getClass().getClassLoader().toString());
    }

    @Override
    public void start() {

        getLogger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                Data data = new Data("Zdanek", counter);

                getLogger().info(verticleId() + " Sending message " + data);

                vertx.eventBus().send(Consumer.CONSUMER_ADDRESS, Json.encode(data));

                counter++;
            }
        });
    }

}
