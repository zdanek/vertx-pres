package http_bench;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

/**
 * Created by bartek on 16.06.15.
 */
public class HttpHelloServer extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        HttpServer httpServer = vertx.createHttpServer();

        httpServer.requestHandler(handler -> {
            handler.response()
                .end("Hello world!");
        }).listen(5100);

        System.out.println("Listening on port 5100");
    }
}
