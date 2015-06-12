package web;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.LoggerFactory;
import pl.zdanek.vertx.BaseVerticle;

public class WebWithEB extends BaseVerticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();


        server.requestHandler(new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                LoggerFactory.getLogger(getClass()).info("Got request " + request.path());
                String path = request.path();
                if ("/".equals(path)) {
                    path = "/eventbus.html";
                }

                request.response().sendFile(ROOT + path);
            }
        });

        createEventBusToJSBridge(server);

        server.listen(8080);

        vertx.setPeriodic(4000L, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {

                LoggerFactory.getLogger(getClass()).info("Sending message to web client");
                vertx.eventBus().publish("web.client", "Message from backend!" );
            }
        });

        LoggerFactory.getLogger(getClass()).info("Server ready!");
    }

    private void createEventBusToJSBridge(HttpServer server) {
        JsonObject config = new JsonObject().put("prefix", "/eventbus");
//        TODO fix here
//        vertx.createSockJSServer(server).bridge(config, new JsonArray(), new JsonArray().add(new JsonObject().put("address", "web.client")));
    }
}
