package shared_data_cluster;

import io.vertx.core.DeploymentOptions;
import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 21.06.15.
 */
public class Starter extends BaseVerticle {

    @Override
    public void start() throws Exception {

        vertx.deployVerticle("shared_data_cluster/Producer.java");
        vertx.deployVerticle(
            "shared_data_cluster/Consumer.java",
            new DeploymentOptions().setInstances(5));
    }
}
