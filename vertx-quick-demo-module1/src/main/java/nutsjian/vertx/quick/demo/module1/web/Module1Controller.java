package nutsjian.vertx.quick.demo.module1.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import nutsjian.vertx.quick.demo.module1.service.Module1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class Module1Controller {

    private static final Logger logger = LoggerFactory.getLogger(Module1Controller.class);

    public static final String PATH = "/module1";

    private final Module1Service module1Service;
    private final Router router;

    @Inject
    public Module1Controller(Vertx vertx, Router mainRouter, Module1Service helloService) {
        // 初始化子路由
        this.router = Router.router(vertx);

        // 在主路由上挂载子路由
        mainRouter.mountSubRouter(PATH, this.router);

        // 子路由配置
        this.router.route().path("/foo").method(HttpMethod.GET).handler(this::foo);

        // 注入业务类、配置类
        this.module1Service = helloService;
    }

    private void foo(RoutingContext context) {
        logger.info("service => " + this.module1Service);
        context.response().end("module1controller say foo");
    }

}
