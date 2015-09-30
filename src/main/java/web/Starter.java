package web;

import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class Starter extends BaseVerticle {
    @Override
    public void start() throws Exception {

        vertx.deployVerticle("web/WebWithEB.java");
        vertx.deployVerticle("web/Producer.java");
//        vertx.deployVerticle("web/NotPermitted.java");
//        vertx.deployVerticle("web/NervousProducer.java");
    }
}
