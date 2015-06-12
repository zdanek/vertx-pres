package eventbus_pubsub;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import pl.zdanek.vertx.BaseVerticle;

public class SleepyConsumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";
    private static final Long SLEEP_TIME_MS = 2000L;

    public SleepyConsumer() {
        sout("SleepyConsumer");
        sout(this.getClass().getClassLoader().toString());
    }

    @Override
    public void start() {
        getLogger().info(vertx.hashCode());
        getLogger().info(verticleId() + "Consumer started! " + hashCode());

        vertx.eventBus().consumer(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {

                getLogger().info(verticleId() + "Received message: " + message.body());
                sleep(SLEEP_TIME_MS);
            }
        });
    }

}
