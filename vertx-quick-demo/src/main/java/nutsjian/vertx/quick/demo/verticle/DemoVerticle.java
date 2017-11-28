package nutsjian.vertx.quick.demo.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import nutsjian.vertx.quick.demo.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class DemoVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(DemoVerticle.class);

    private final Router router;
    private final AppConfig appConfig;

    @Inject
    public DemoVerticle(Router router, AppConfig appConfig) {
        this.router = router;
        this.appConfig = appConfig;
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer()
            .requestHandler(this.router::accept)
            .listen(this.appConfig.getPort(), res -> {
                if (res.succeeded()) {
                    logger.info("http rest service started in port " + appConfig.getPort() + " succeed !");
                } else {
                    logger.error("http rest service started failed !");
                }
            });
    }
}
