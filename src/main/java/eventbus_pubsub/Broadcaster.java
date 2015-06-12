package eventbus_pubsub;

import eventbus_p2p.Consumer;
import io.vertx.core.Handler;
import pl.zdanek.vertx.BaseVerticle;


public class Broadcaster extends BaseVerticle {

    private static final long PERIOD_MS = 1000L;

    private int counter = 0;

    public Broadcaster() {
        sout("Broadcaster");
        sout(this.getClass().getClassLoader().toString());
    }

    @Override
    public void start() {

        getLogger().info("Broadcaster started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                getLogger().info(verticleId() + " Broadcasting message " + counter);

                vertx.eventBus().publish(Consumer.CONSUMER_ADDRESS,
                        "Message " + counter);
                counter++;
            }
        });
    }

}
