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
public class UserEndpointServer extends AbstractVerticle {

    private static final String JSON_CT = "application/json";

    @Override
    public void start() throws Exception {

        Router router = new RouterImpl(vertx);

        router.get("/users/:uid")
        .handler(ctx -> {
            String id = ctx.request().getParam("uid");
            JsonObject user = new JsonObject().put("id", id).put("name", "bartek");
            ctx.response().end(user.encode());
        });

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(8080);


        router.post("/some/post/path")
        router.put()
        router.get("/user/data").consumes("application/json")
        router.get("/user/data").consumes("text/html")


        LoggerFactory.getLogger(getClass()).info("Deployed " + UserEndpointServer.class.getName());
    }
}

