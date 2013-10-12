package shared_data;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("shared_data/Producer.java");
        container.deployVerticle("shared_data/Consumer.java", 5);
    }
}
