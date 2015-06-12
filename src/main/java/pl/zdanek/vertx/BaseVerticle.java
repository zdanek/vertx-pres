package pl.zdanek.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * @author bzd
 */
public abstract class BaseVerticle extends AbstractVerticle {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    protected String verticleId() {
        return ">" + hexHashCode() + " [" + Thread.currentThread().getName() + "] ";
    }

    protected void sout(String mesg) {
        System.out.println(mesg);
    }

    protected void sleep(Long sleepTimeMs) {
        getLogger().info(verticleId() + "Sleeping " + sleepTimeMs);
        try {
            Thread.sleep(sleepTimeMs);
        } catch (InterruptedException e) {
            getLogger().error("Error sleeping", e);
        }

    }

    protected String hexHashCode() {
        return Integer.toHexString(hashCode());
    }

}
