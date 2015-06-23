package web;

import pl.zdanek.vertx.BaseVerticle;

public class PlainWebServer extends BaseVerticle {

    private final static String ROOT = "web";

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(request -> {

            getLogger().info("Got request " + request.path());
            String path = request.path();
            if ("/".equals(path)) {
                path = "/index.html";
            }

            request.response().sendFile(ROOT + path);
        }).listen(8080);

        getLogger().info("Server ready!");
    }
}
