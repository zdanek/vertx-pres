package eventbus_pubsub;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("eventbus_pubsub/Broadcaster.java");
        container.deployVerticle("eventbus_pubsub/Consumer.java", 2);
        container.deployVerticle("eventbus_pubsub/SleepyConsumer.java");
    }
}
