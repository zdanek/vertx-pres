package publish;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.logging.LoggerFactory;


public class Producer extends AbstractVerticle {

    private static final long PERIOD_MS = 1000L;

    private int counter = 0;

    @Override
    public void start() {

        LoggerFactory.getLogger(getClass()).info("Producer started");

        vertx.setPeriodic(PERIOD_MS, new Handler<Long>() {

            @Override
            public void handle(Long timerID) {
                LoggerFactory.getLogger(getClass()).info(" Sending message " + counter);

                vertx.eventBus().publish(Consumer.CONSUMER_ADDRESS,
                        "Message " + counter);
                counter++;
            }
        });
    }

}
