package web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.LoggerFactory;

public class WebServer extends AbstractVerticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                LoggerFactory.getLogger(getClass()).info("Got request " + request.path());
                String path = request.path();
                if ("/".equals(path)) {
                    path = "/index.html";
                }

                request.response().sendFile(ROOT + path);
            }
        }).listen(8080);

        LoggerFactory.getLogger(getClass()).info("Server ready!");
    }
}
