package first;

import org.vertx.java.core.Handler;
import org.vertx.java.platform.Verticle;


public class Producer extends Verticle {

    private static final long PERIOD_MS = 1000L;

    private int counter = 0;

    @Override
    public void start() {

        getContainer().logger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                getContainer().logger().info(" Sending message " + counter);

                vertx.eventBus().publish(Consumer.CONSUMER_ADDRESS,
                        "Message " + counter);
                counter++;
            }
        });
    }

}
