package shared_data_safe;

import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public class Starter extends Verticle {
    @Override
    public void start() {
        container.deployVerticle("shared_data_safe/DataRepo.java");
        container.deployVerticle("shared_data_safe/Producer.java");
        container.deployVerticle("shared_data_safe/Consumer.java", 5);
    }
}
