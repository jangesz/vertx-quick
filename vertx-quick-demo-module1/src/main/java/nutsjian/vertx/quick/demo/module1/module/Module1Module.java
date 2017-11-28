package nutsjian.vertx.quick.demo.module1.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import nutsjian.vertx.quick.demo.module1.service.Module1Service;
import nutsjian.vertx.quick.demo.module1.service.impl.Module1ServiceImpl;
import nutsjian.vertx.quick.demo.module1.web.Module1Controller;

public class Module1Module extends AbstractModule {
    @Override
    protected void configure() {
        // 注册web controller
        bind(Module1Controller.class).asEagerSingleton();

        // 注册hello service
        bind(Module1Service.class).to(Module1ServiceImpl.class).in(Singleton.class);
    }
}
