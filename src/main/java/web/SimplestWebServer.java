package web;

import io.vertx.core.AbstractVerticle;

public class SimplestWebServer extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        vertx.createHttpServer().requestHandler(
            httpServerRequest -> {
                httpServerRequest.response().end(
                    "Greetings from Vert.x");
            }
        ).listen(8080);
    }
}
