package eventbus_p2p;

import pl.zdanek.vertx.BaseVerticle;


public class ProducerWithResponseHandler extends BaseVerticle {

    private static final long PERIOD_MS = 2000L;

    private int counter = 0;

    public ProducerWithResponseHandler() {
        sout("Producer");
        sout(this.getClass().getClassLoader().toString());
    }

    @Override
    public void start() {

        getLogger().info("Producer started");

        vertx.setPeriodic(PERIOD_MS, timerID -> {
            getLogger().info("======\n" + verticleId() +
                    "\nSending message " + counter + "\n");

            vertx.eventBus().send(Consumer.CONSUMER_ADDRESS,
                "Job_to_be_done-" + counter, deliveryStat ->
                {
                    if (deliveryStat.succeeded()) {
                        getLogger().info("Delivered");
                    } else {
                        getLogger().error("Failed!",
                            deliveryStat.cause());
                    }
                }
            );

            counter++;
        });
    }

}
