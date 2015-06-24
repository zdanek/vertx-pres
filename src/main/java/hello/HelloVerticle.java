package hello;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {
    @Override
    public void start() {
        LoggerFactory.getLogger(getClass()).info(
            "Hola Barcelona!");
    }
}

