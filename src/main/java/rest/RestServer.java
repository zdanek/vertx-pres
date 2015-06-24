package rest;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class RestServer extends BaseVerticle {
    private static final String JSON_CT = "application/json";
//    public static final String CT_HEADER = "Content-Type";

    public RestServer() {
        System.setProperty("vertx.disableFileCaching", "true");
    }

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router.route().handler( req -> {
            getLogger().info(String.format("Request [%s], " +
                "method: [%s], accepts: [%s]",
                req.request().path(),
                req.request().method(),
                req.request().getHeader("Accept")
            ));

            req.next();
        });

        router.route().consumes("text/html*").handler(req -> {

            req.response()
                .putHeader(HttpHeaders.CONTENT_TYPE,
                    HttpHeaders.TEXT_HTML)
                .end("<b>This meant to be REST server</b>");
//            req.next();
        });


        router.route().consumes(JSON_CT).handler(
            req -> {

                req.response().putHeader(HttpHeaders.CONTENT_TYPE,
                    JSON_CT)
                    .end("[ok]");

            });

        router.route("/error").handler(req -> {
            throw new RuntimeException("Some error happened");
        });


        router.route().produces(JSON_CT).failureHandler(req -> {

            getLogger().error("Route failure handler",
                req.failure());
            req.response().setStatusCode(500)
//                .putHeader(CT_HEADER, JSON_CT)
                .end("{error: " + req.failure().getMessage()
                    + ", code: 500 }");
        });


        router.route().failureHandler(req -> {

            getLogger().error("Route failure handler",
                req.failure());
            req.response().setStatusCode(501)
                .end("Bad error happened");
        });

        router.exceptionHandler(exception -> {
            getLogger().error("This is custom exception handler." +
                    " Error: ",
                exception);

        });
        server.requestHandler(router::accept);
        server.listen(8080);

        getLogger().info("Server is ready. Take a REST");

    }
}
