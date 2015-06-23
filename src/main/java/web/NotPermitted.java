package web;

import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class NotPermitted extends BaseVerticle {
    @Override
    public void start() throws Exception {

        vertx.setPeriodic(1000L, timerId -> {
            getLogger().info("Secret message sent");
            vertx.eventBus().publish("secret.address",
                "This should not go to the Web!");
            }
        );
    }
}
