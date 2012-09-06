package example.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.net.ServerSocket;

@SuppressWarnings("UnusedDeclaration")
public class WebServer {

    private final Server server;
    private final int port;

    public WebServer() {
        this(findFreePort());
    }

    public WebServer(int port) {
        setupSimpleLoggingFacade();

        this.server = new Server(port);
        this.port = port;

        // We are going to setup a webapp without using a web.xml
        ServletContextHandler sch = new ServletContextHandler(this.server, "/example");

        configureJerseyFramework(sch);

        // Must add DefaultServlet for embedded Jetty. Failing to do this will cause 404 errors.
        // Jetty adds this by default when it configures a web application via web.xml
        sch.addServlet(DefaultServlet.class, "/");

        // load static content from this root location
        sch.setResourceBase("common/src/main/webapp");
    }

    protected void configureJerseyFramework(ServletContextHandler sch) {
    }

    public int port() {
        return port;
    }

    public void startAndWait() throws Exception {
        server.setStopAtShutdown(true);
        server.start();
        server.join();
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupSimpleLoggingFacade() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    private static int findFreePort() {
        try {
            ServerSocket socket = new ServerSocket(0);
            int port = socket.getLocalPort();
            socket.close();
            return port;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
