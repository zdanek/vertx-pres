package embedded;

import embedded.consumer.EmbeddedConsumer;
import embedded.producer.EmbeddedProducer;
import io.vertx.core.Vertx;

/**
 * Created by bartek on 23.06.15.
 */
public class Starter {


    public static void main(String[] args) {
        new Starter().go();
    }

    private void go() {
        Vertx instance = Vertx.vertx();
        instance.deployVerticle(EmbeddedProducer.class.getName());
        instance.deployVerticle(EmbeddedConsumer.class.getName());
    }

}
