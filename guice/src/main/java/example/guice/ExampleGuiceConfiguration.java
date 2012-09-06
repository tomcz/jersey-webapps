package example.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ExampleGuiceConfiguration extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ExampleServletModule(), new ResourceModule(), new RepositoryModule());
    }
}
