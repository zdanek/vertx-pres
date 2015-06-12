package publish;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("publish/Producer.java");
        vertx.deployVerticle("publish/Consumer.java", new DeploymentOptions().setInstances(10));
    }
}
