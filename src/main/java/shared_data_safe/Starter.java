package shared_data_safe;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("shared_data_safe/DataRepo.java");
        vertx.deployVerticle("shared_data_safe/Producer.java");
        vertx.deployVerticle("shared_data_safe/Consumer.java", new DeploymentOptions().setInstances(5));
    }
}
