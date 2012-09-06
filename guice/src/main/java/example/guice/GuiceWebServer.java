package example.guice;

import com.google.inject.servlet.GuiceFilter;
import example.jetty.WebServer;
import org.eclipse.jetty.servlet.ServletContextHandler;

@SuppressWarnings("UnusedDeclaration")
public class GuiceWebServer extends WebServer {

    public GuiceWebServer() {
        super();
    }

    public GuiceWebServer(int port) {
        super(port);
    }

    @Override
    protected void configureJerseyFramework(ServletContextHandler sch) {
        sch.addEventListener(new ExampleGuiceConfiguration());
        sch.addFilter(GuiceFilter.class, "/*", null);
    }

    public static void main(String[] args) throws Exception {
        new GuiceWebServer(8080).startAndWait();
    }
}
