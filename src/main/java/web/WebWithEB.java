package web;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.platform.Verticle;

import java.lang.Override;

public class WebWithEB extends Verticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();


        server.requestHandler(new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                getContainer().logger().info("Got request " + request.path());
                String path = request.path();
                if ("/".equals(path)) {
                    path = "/index.html";
                }

                request.response().sendFile(ROOT + path, ROOT + "/404.html");
            }
        });

        createEventBusToJSBridge(server);

        server.listen(8080);

        getContainer().logger().info("Server ready!");
    }

    private void createEventBusToJSBridge(HttpServer server) {
        JsonObject config = new JsonObject().putString("prefix", "/eventbus");
        vertx.createSockJSServer(server).bridge(config, new JsonArray(), new JsonArray());
    }
}