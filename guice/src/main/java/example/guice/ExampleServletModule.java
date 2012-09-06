package example.guice;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ExampleServletModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        serve("/resource/*").with(GuiceContainer.class);
    }
}
