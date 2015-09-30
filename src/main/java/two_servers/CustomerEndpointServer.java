package two_servers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.impl.RouterImpl;

/**
 * Created by bartek on 30.09.15.
 */
public class CustomerEndpointServer extends AbstractVerticle {

    private static final String JSON_CT = "application/json";

    @Override
    public void start() throws Exception {

        Router router = new RouterImpl(vertx);

        router.get("/customers/:uid")
            //.consumes(JSON_CT).
        .handler( ctx -> {
            String id = ctx.request().getParam("uid");
            JsonObject user = new JsonObject().put("id", id).put("name", "SuperCustomer");
            ctx.response().end(user.encode());
        });

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(8080);

        LoggerFactory.getLogger(getClass()).info("Deployed " + CustomerEndpointServer.class.getName());
    }
}
