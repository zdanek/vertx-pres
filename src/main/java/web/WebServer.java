package web;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import pl.zdanek.vertx.BaseVerticle;

public class WebServer extends BaseVerticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                getLogger().info("Got request " + request.path());
                String path = request.path();
                if ("/".equals(path)) {
                    path = "/index.html";
                }

                request.response().sendFile(ROOT + path);
            }
        }).listen(8080);

        getLogger().info("Server ready!");
    }
}
