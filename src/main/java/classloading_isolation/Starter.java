package classloading_isolation;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * Created by bartek on 25.06.15.
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        vertx.deployVerticle("classloading_isolation/Instance.java",
            new DeploymentOptions().setInstances(5));
    }
}
