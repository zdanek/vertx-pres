package pl.zdanek.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by bartek on 25.06.15.
 */
public final class LogSupport {
    private LogSupport(){
    }

    public static void logVerticle(Verticle verticle) {
        LoggerFactory.getLogger(verticle.getClass().getName())
            .info(">" + hexHashCode(verticle) +
                " [" + currentThreadName() + "] ");
        ;
    }

    private static String currentThreadName() {
        return Thread.currentThread().getName();
    }

    private static String hexHashCode(Verticle verticle) {
        return Integer.toHexString(verticle.hashCode());
    }
}
