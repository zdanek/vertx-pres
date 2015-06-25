package reactor;

import io.vertx.core.AbstractVerticle;

/**
 * Created by bartek on 25.06.15.
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        vertx.deployVerticle("reactor/First.java");
        //add more instances
/*
        vertx.deployVerticle("reactor/First.java", new
            DeploymentOptions().setInstances(16));
            */
        vertx.deployVerticle("reactor/Second.java");
    }
}
