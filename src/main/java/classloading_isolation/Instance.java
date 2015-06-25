package classloading_isolation;

import pl.zdanek.vertx.BaseVerticle;
import pl.zdanek.vertx.LogSupport;

/**
 * Created by bartek on 25.06.15.
 */
public class Instance extends BaseVerticle {

    public static Integer staticValue = 0;

    @Override
    public void start() throws Exception {
        getLogger().info("Starting new instance");
        staticValue++;

        vertx.setPeriodic(3000L, timerId -> {

            LogSupport.logVerticle(Instance.this);
            getLogger().info("StaticValue: " + staticValue);
            staticValue++;
        });

    }
}
