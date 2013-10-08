package eventbus_p2p;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("eventbus_p2p/Producer.java");
        container.deployVerticle("eventbus_p2p/Consumer.java", 5);
    }
}
