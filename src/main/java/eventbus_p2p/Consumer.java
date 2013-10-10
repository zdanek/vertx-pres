package eventbus_p2p;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class Consumer extends Verticle {

    public static final String CONSUMER_ADDRESS = "consumer.address";
    private static int workerCnt = 0;
    private int workerNum = -1;

    public Consumer() {
        System.out.println("Consum");
        System.out.println(this.getClass().getClassLoader().toString());
        workerNum = getWorkerUniqueNum();
    }

    @Override
    public void start() {
        System.out.println(vertx.hashCode());

        getContainer().logger().info("Consumer started! " + hashCode());

        vertx.eventBus().registerHandler(CONSUMER_ADDRESS, new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> message) {
                // Reply to it
                getContainer().logger().info(verticleId()+ " Received message: " + message.body());
//                message.reply("Got it!");
            }
        });
    }

    private String verticleId() {
        return ">" + Integer.toHexString(hashCode()) + " [" + Thread.currentThread().getName() + "]";
    }

    private static int getWorkerUniqueNum() {
        synchronized (Consumer.class) {
            workerCnt++;
            return workerCnt;
        }
    }


}
