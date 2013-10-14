package pl.zdanek.vertx;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

/**
 * @author bzd
 */
public abstract class BaseVerticle extends Verticle {

    protected Logger getLogger() {
        return getContainer().logger();
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
