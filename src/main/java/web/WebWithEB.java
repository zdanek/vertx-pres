package web;

import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FaviconHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import pl.zdanek.vertx.BaseVerticle;

public class WebWithEB extends BaseVerticle {

    private final static String ROOT = "web";

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions()
            .addOutboundPermitted(new PermittedOptions()
//                .setAddressRegex(".*"));
                .setAddress("web.client"));
        sockJSHandler.bridge(options);

        router.route("/eventbus/*").handler(sockJSHandler);

        router.route().handler(FaviconHandler.create
            (ROOT + "/favicon.ico"));

        router.route().handler(StaticHandler.create(ROOT));

        server.requestHandler(router::accept).listen(8080);

        getLogger().info("Server ready...");
    }

}
