package example.pico;

import com.sun.jersey.spi.pico.container.servlet.PicoServlet;
import example.jetty.WebServer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@SuppressWarnings("UnusedDeclaration")
public class PicoWebServer extends WebServer {

    public PicoWebServer() {
        super();
    }

    public PicoWebServer(int port) {
        super(port);
    }

    @Override
    protected void configureJerseyFramework(ServletContextHandler sch) {
        sch.addEventListener(new ExamplePicoConfiguration());

        ServletHolder servlet = new ServletHolder("pico", PicoServlet.class);
        servlet.setInitParameter(PicoServlet.PICO_PROVIDER_KEY, ExamplePicoConfiguration.PICO_PROVIDER_KEY);
        servlet.setInitOrder(1);

        sch.addServlet(servlet, "/resource/*");
    }

    public static void main(String[] args) throws Exception {
        new PicoWebServer(8080).startAndWait();
    }
}
