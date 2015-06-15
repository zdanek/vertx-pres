package eventbus_p2p;


import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import pl.zdanek.vertx.BaseVerticle;

public class ConsumerSimple extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start() {
        logger.info("Consumer started! ");

        vertx.eventBus().consumer(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                logger.info(" Received message: " + message.body());
            }
        });
    }
}
