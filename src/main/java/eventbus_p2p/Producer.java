package eventbus_p2p;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;
import pl.zdanek.vertx.BaseVerticle;


public class Producer extends BaseVerticle {

    private static final long PERIOD_MS = 1000L;

    private int counter = 0;

    public Producer() {
        sout("Producer");
        sout(this.getClass().getClassLoader().toString());
    }

    @Override
    public void start() {

        getLogger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                getLogger().info(verticleId() + " Sending message " + counter);

                vertx.eventBus().send(Consumer.CONSUMER_ADDRESS,
                        "Message " + counter, new Handler<Message<String>>() {

                    @Override
                    public void handle(Message<String> reply) {
                        sout("Received reply: " + reply.body());
                    }
                });
                counter++;
            }
        });
    }

}
