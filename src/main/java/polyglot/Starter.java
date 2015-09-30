package polyglot;

import io.vertx.core.AbstractVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class Starter extends AbstractVerticle {

    @Override
    public void stop() throws Exception {

        vertx.deployVerticle("polyglot/Producer.rb", res -> {
            System.out.println("ddd");
            System.out.println(res.succeeded());
        });
        vertx.deployVerticle("polyglot/Receiver.js");
    }
}
