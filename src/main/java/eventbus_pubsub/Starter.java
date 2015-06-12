package eventbus_pubsub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("eventbus_pubsub/Broadcaster.java");
        vertx.deployVerticle("eventbus_pubsub/Consumer.java", new DeploymentOptions().setInstances(2));
        vertx.deployVerticle("eventbus_pubsub/SleepyConsumer.java");
    }
}
