package eventbus_pubsub;

import eventbus_p2p.Consumer;
import pl.zdanek.vertx.BaseVerticle;


public class Broadcaster extends BaseVerticle {

    private static final long PERIOD_MS = 1000L;

    private int counter = 0;

    @Override
    public void start() {

        getLogger().info("Broadcaster started");
        vertx.setPeriodic(PERIOD_MS, timerID -> {

            getLogger().info("=====\n" + verticleId()
                + "\nBroadcasting message " + counter + "\n");
            vertx.eventBus().publish(Consumer.CONSUMER_ADDRESS,
                    "Message " + counter);
            counter++;
        });
    }

}
