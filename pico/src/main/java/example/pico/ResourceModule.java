package example.pico;

import com.google.common.collect.ImmutableList;
import com.sun.jersey.spi.pico.container.servlet.BlankPicoConfiguration;
import example.domain.resources.DocumentResource;
import example.domain.resources.IndexResource;
import example.domain.resources.StatusResource;
import org.picocontainer.MutablePicoContainer;

import java.util.List;
import java.util.Set;

public class ResourceModule extends BlankPicoConfiguration {

    private final List<Class<?>> resourceClasses;

    public ResourceModule() {
        this.resourceClasses = ImmutableList.<Class<?>>builder()
                .add(StatusResource.class)
                .add(IndexResource.class)
                .add(DocumentResource.class)
                .build();
    }

    @Override
    public void registerResourceScope(MutablePicoContainer scope) {
        for (Class<?> resourceClass : resourceClasses) {
            scope.addComponent(resourceClass);
        }
    }

    @Override
    public void registerResources(Set<Class<?>> resources) {
        resources.addAll(resourceClasses);
    }
}
