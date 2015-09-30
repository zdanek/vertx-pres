package first;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by bartek on 24.06.15.
 */
public class First extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        LoggerFactory.getLogger(getClass().getName())
            .info("Hello Vert.x");
    }
}
