import org.vertx.java.core.Future;
import org.vertx.java.platform.Verticle;

import java.lang.Override;
import java.lang.Void;

public class HelloClient extends Verticle {

    @Override
    public void start() {
        getContainer().logger().info("Hello vert.x");
    }

    @Override
    public void start(Future<Void> startedResult) {
        getContainer().logger().info(startedResult.complete());
    }
}