package two_servers;

import io.vertx.core.AbstractVerticle;

/**
 * @author bzd
 */
public class Starter extends AbstractVerticle {
    @Override
    public void start() {
        vertx.deployVerticle("two_servers/CustomerEndpointServer.java");
        vertx.deployVerticle("two_servers/UserEndpointServer.java");
    }
}
