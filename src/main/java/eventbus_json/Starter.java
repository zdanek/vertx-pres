package eventbus_json;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("eventbus_json/Producer.java");
        container.deployVerticle("eventbus_json/Consumer.java");
    }
}
