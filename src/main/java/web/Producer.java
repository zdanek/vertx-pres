package web;

import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class Producer extends BaseVerticle {
    @Override
    public void start() throws Exception {

        vertx.setPeriodic(2000L, timerId -> {
                getLogger().info(
                    "Sending a message to web client");

                vertx.eventBus().publish(
                    "web.client",
                    "Hello from publisher!");
            }
        );
    }
}
