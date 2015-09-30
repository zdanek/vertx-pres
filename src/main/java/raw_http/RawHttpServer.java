package raw_http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by bartek on 30.09.15.
 */
public class RawHttpServer extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(RawHttpServer.class);

    @Override
    public void start() throws Exception {

        int port = 8080;
        vertx.createHttpServer().requestHandler( req -> {
            LOG.info(String.format("Got request [%s]", req.path()));
            switch (req.path()) {
                case "/" : req.response().end("Ok. Here's root"); break;
                case "/other": req.response().end("Other things..."); break;
                default:
                    req.response().setStatusCode(404).end("Sorry. Unknown resource!");
            }
        }).listen(port);

        LOG.info(String.format("Serving on port [%s]", port));
    }
}
