package web;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

import java.lang.Override;

public class WebServer extends Verticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                getContainer().logger().info("Got request " + request.path());
                String path = request.path();
                if ("/".equals(path)) {
                    path = "/index.html";
                }

                request.response().sendFile(ROOT + path, ROOT + "/404.html");
            }
        }).listen(8080);

        getContainer().logger().info("Server ready!");
    }
}