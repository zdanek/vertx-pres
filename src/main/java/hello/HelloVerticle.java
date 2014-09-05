package hello;

import org.vertx.java.platform.Verticle;

public class HelloVerticle extends Verticle {
    @Override
    public void start() {
        container.logger().info("Hello JDay");
    }
}

