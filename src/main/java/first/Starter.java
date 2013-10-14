package first;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("first/Producer.java");
        container.deployVerticle("first/Consumer.java");
    }
}
