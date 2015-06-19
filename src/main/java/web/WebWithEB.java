package web;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import pl.zdanek.vertx.BaseVerticle;

public class WebWithEB extends BaseVerticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();


        server.requestHandler(new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                getLogger().info("Got request " + request.path());
                String path = request.path();
                if ("/".equals(path)) {
                    path = "/eventbus.html";
                }

                request.response().sendFile(ROOT + path);
            }
        });

        NetServer nserver = vertx.createNetServer(
            new NetServerOptions().setPort(1234).setHost("localhost")
        );
        nserver.connectHandler((NetSocket sock) -> {

            sock.handler(buffer -> {
                // Write the data straight back
                sock.write(buffer);
            });
        }).listen();

        createEventBusToJSBridge(server);

        server.listen(8080, res -> {
            if (res.succeeded()) {
                getLogger().info("Sucessfuly started HTTP server");
            }
            if (res.failed()) {
                getLogger().error("Error starting HTTP server", res.cause());
            }
        });

        vertx.setPeriodic(4000L, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {

                LoggerFactory.getLogger(getClass());
                getLogger().info("Sending message to web client");
                vertx.eventBus().publish("web.client", "Message from backend!" );
            }
        });

        getLogger().info("Server ready!");
    }

    private void createEventBusToJSBridge(HttpServer server) {
        JsonObject config = new JsonObject().put("prefix", "/eventbus");
//        TODO fix here
//        vertx.createSockJSServer(server).bridge(config, new JsonArray(), new JsonArray().add(new JsonObject().put("address", "web.client")));

        server.requestHandler();

        Router router = Router.router(vertx);

        // Allow outbound traffic to the news-feed address

        BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("news-feed"));

        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {

            // You can also optionally provide a handler like this which will be passed any events that occur on the bridge
            // You can use this for monitoring or logging, or to change the raw messages in-flight.
            // It can also be used for fine grained access control.

            if (event.type() == BridgeEvent.Type.SOCKET_CREATED) {
                System.out.println("A socket was created");
            }

            // This signals that it's ok to process the event
            event.complete(true);

        }));

        // Serve the static resources
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
