package nutsjian.vertx.quick.demo.config;

import com.google.inject.Singleton;

@Singleton
public class AppConfig {
    private int port = 9999;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
