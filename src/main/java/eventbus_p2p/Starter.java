package eventbus_p2p;


import io.vertx.core.AbstractVerticle;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("eventbus_p2p/Producer.java");
        vertx.deployVerticle("eventbus_p2p/ConsumerSimple.java");
//        vertx.deployVerticle("eventbus_p2p/Consumer.java");
//        vertx.deployVerticle("eventbus_p2p/Consumer.java", 3);
    }
}
