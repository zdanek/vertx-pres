package embedded.producer;

import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class EmbeddedProducer extends BaseVerticle {
    public static final String PRODUCER_ADDRESS = "producer.address";

    @Override
    public void start() throws Exception {
        getLogger().info("Starting Producer");

        vertx.setPeriodic(2000L, timerId -> {
             getLogger().info("Sending message");
            vertx.eventBus().publish(PRODUCER_ADDRESS,
                    "Message from producer");
            }
        );

    }
}
