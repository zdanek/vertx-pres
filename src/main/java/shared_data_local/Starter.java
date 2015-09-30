package shared_data_local;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("shared_data_local/Producer.java");
        vertx.deployVerticle("shared_data_local/Consumer.java",
            new DeploymentOptions().setInstances(5));
    }
}
