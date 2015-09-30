package eventbus_p2p;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("eventbus_p2p/Producer.java");
//        vertx.deployVerticle("eventbus_p2p/ConsumerSimple.java");
        vertx.deployVerticle("eventbus_p2p/ConsumerSimple.java" ,
            new DeploymentOptions().setInstances(5));

//        vertx.deployVerticle("eventbus_p2p/ProducerWithResponseHandler.java");
//        vertx.deployVerticle("eventbus_p2p/Consumer.java");
//        vertx.deployVerticle("eventbus_p2p/Consumer.java", new
//            DeploymentOptions().setInstances(5));
    }
}
