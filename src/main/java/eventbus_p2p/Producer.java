package eventbus_p2p;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;


public class Producer extends Verticle {

    private static final long PERIOD_MS = 20L;

    private int counter = 0;

    @Override
    public void start() {

        getLogger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                getLogger().info("Sending message " + counter);

                vertx.eventBus().send(Consumer.CONSUMER_ADDRESS, "Message " + counter, new Handler<Message<String>>() {

                    @Override
                    public void handle(Message<String> reply) {
                        System.out.println("Received reply: " + reply.body());
                    }
                });
                counter++;
            }
        });
    }

    private Logger getLogger() {
        return getContainer().logger();
    }
}
