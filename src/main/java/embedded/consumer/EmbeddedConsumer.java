package embedded.consumer;

import embedded.producer.EmbeddedProducer;
import pl.zdanek.vertx.BaseVerticle;

/**
 * Created by bartek on 23.06.15.
 */
public class EmbeddedConsumer extends BaseVerticle {
    @Override
    public void start() throws Exception {
        getLogger().info("Startig Consumer");
        vertx.eventBus().consumer(
            EmbeddedProducer.PRODUCER_ADDRESS,
            event -> {

                getLogger().info("Got message: " + event.body());

            }
            );


    }
}
