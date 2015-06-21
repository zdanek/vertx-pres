package eventbus_pubsub;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * Created by bartek on 21.06.15.
 */
public class FatStarter {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(Broadcaster.class.getName());
        vertx.deployVerticle(SleepyConsumer.class.getName());

        vertx.deployVerticle(Consumer.class.getName(),
            new DeploymentOptions()
                .setInstances(2));

    }

}
