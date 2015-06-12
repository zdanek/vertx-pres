import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.impl.LoggerFactory;

import java.lang.Override;
import java.lang.Void;

public class HelloClient extends AbstractVerticle {

    @Override
    public void start() {
        Logger log = LoggerFactory.getLogger(HelloClient.class);
        log.info("Hello vert.x");

    }

/*    @Override
    public void start(Future<Void> startedResult) {
        LoggerFactory.getLogger(getClass()).info(startedResult.complete());
    }*/
}
