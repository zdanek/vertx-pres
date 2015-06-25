package reactor;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import pl.zdanek.vertx.LogSupport;

/**
 * Created by bartek on 25.06.15.
 */
public class First extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        vertx.setPeriodic(3000L, new Handler<Long>() {
            @Override
            public void handle(Long timerId) {

                LogSupport.logVerticle(First.this);
            }
        });

    }
}
