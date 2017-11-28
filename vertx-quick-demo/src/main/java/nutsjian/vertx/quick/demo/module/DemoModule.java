package nutsjian.vertx.quick.demo.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import nutsjian.vertx.quick.demo.config.AppConfig;
import nutsjian.vertx.quick.demo.module1.module.Module1Module;
import nutsjian.vertx.quick.demo.service.HelloService;
import nutsjian.vertx.quick.demo.service.impl.HelloServiceImpl;
import nutsjian.vertx.quick.demo.web.HelloController;

public class DemoModule extends AbstractModule {
    @Override
    protected void configure() {
        // 注册web controller
        bind(HelloController.class).asEagerSingleton();

        // 注册hello service
        bind(HelloService.class).to(HelloServiceImpl.class).in(Singleton.class);

        install(new Module1Module());
    }

    // 初始化主路由，子路由直接mountSubPoint到主路由上，实现web模块的分离
    @Provides @Singleton public Router mainRouter(Vertx vertx, AppConfig appConfig) {
        final Router mainRouter = Router.router(vertx);
        mainRouter.route().consumes("application/json");
        mainRouter.route().produces("application/json");
        mainRouter.route().handler(BodyHandler.create());
        return mainRouter;
    }

}
