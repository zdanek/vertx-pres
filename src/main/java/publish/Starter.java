package publish;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("publish/Producer.java");
        container.deployVerticle("publish/Consumer.java", 4);
    }
}
