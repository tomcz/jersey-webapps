package example.pico;

import com.sun.jersey.spi.pico.container.servlet.PicoConfiguration;
import com.sun.jersey.spi.pico.container.servlet.PicoConfigurations;
import com.sun.jersey.spi.pico.container.servlet.ScopedPicoContainerProvider;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ExamplePicoConfiguration implements ServletContextListener {

    public static final String PICO_PROVIDER_KEY = "example.pico.provider";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        PicoConfiguration configuration = new PicoConfigurations(new ResourceModule(), new RepositoryModule());
        context.setAttribute(PICO_PROVIDER_KEY, new ScopedPicoContainerProvider(configuration));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
