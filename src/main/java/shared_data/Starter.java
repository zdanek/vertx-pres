package shared_data;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("shared_data/Producer.java");
        vertx.deployVerticle("shared_data/Consumer.java", new DeploymentOptions().setInstances(5));
    }
}
