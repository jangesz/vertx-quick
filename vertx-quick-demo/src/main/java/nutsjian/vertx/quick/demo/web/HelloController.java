package nutsjian.vertx.quick.demo.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import nutsjian.vertx.quick.demo.config.AppConfig;
import nutsjian.vertx.quick.demo.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    public static final String PATH = "/hello";

    private final HelloService helloService;
    private final AppConfig appConfig;
    private final Router router;

    @Inject
    public HelloController(Vertx vertx, Router mainRouter, HelloService helloService, AppConfig appConfig) {
        // 初始化子路由
        this.router = Router.router(vertx);

        // 在主路由上挂载子路由
        mainRouter.mountSubRouter(PATH, this.router);

        // 子路由配置
        this.router.route().path("/greeting").method(HttpMethod.GET).handler(this::greeting);

        // 注入业务类、配置类
        this.helloService = helloService;
        this.appConfig = appConfig;
    }

    private void greeting(RoutingContext context) {
        logger.info("config => " + this.appConfig);
        logger.info("service => " + this.helloService);
        context.response().end("hellocontroller says greeting");
    }


}
