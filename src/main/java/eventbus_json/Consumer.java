package eventbus_json;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import pl.zdanek.vertx.BaseVerticle;

public class Consumer extends BaseVerticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";

    @Override
    public void start() {
        getLogger().info("Consumer started! " + hashCode());

        vertx.eventBus().consumer(
            CONSUMER_ADDRESS, new Handler<Message<String>>() {
                @Override
                public void handle(Message<String> message) {
                    Data data = Json.decodeValue(message.body(), Data.class);
                    getLogger().info(verticleId() + " Received message: " + message.body() + " -> " + data);
                }
            });
    }

}
