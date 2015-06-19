package http_bench;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

/**
 * Created by bartek on 16.06.15.
 */
public class HttpFileServer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(handler -> {
            handler.response().sendFile("http_bench/index.html");
        }).listen(5100);

        System.out.println("Serving file on 5100");
    }
}
