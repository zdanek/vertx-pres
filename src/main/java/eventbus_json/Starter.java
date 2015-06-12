package eventbus_json;

import io.vertx.core.AbstractVerticle;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("eventbus_json/Producer.java");
        vertx.deployVerticle("eventbus_json/Consumer.java");
    }
}
