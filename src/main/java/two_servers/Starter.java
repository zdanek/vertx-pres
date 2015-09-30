package two_servers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle(CustomerEndpointServer.class.getName());
        vertx.deployVerticle(UserEndpointServer.class.getName());
    }

    public static void main(String args[]) {
        Vertx.vertx().deployVerticle(Starter.class.getName());
    }
}
