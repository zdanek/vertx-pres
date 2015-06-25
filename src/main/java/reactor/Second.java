package reactor;

import io.vertx.core.Handler;
import pl.zdanek.vertx.BaseVerticle;
import pl.zdanek.vertx.LogSupport;

/**
 * Created by bartek on 25.06.15.
 */
public class Second extends BaseVerticle {

    @Override
    public void start() throws Exception {

        vertx.setPeriodic(3000L, new Handler<Long>() {
            @Override
            public void handle(Long timerId) {
                LogSupport.logVerticle(Second.this);
//                sleep(3000L);
            }
        });

    }
}
