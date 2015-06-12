package jday;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * Created by bartek on 06.09.14.
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("jday/FirstVerticle.java", new DeploymentOptions().setInstances(10));
        vertx.deployVerticle("jday/Producer.java");

    }
}
