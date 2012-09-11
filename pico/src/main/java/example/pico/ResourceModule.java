package example.pico;

import com.sun.jersey.spi.pico.container.servlet.ResourceConfiguration;
import example.domain.resources.DocumentResource;
import example.domain.resources.IndexResource;
import example.domain.resources.StatusResource;

public class ResourceModule extends ResourceConfiguration {

    public ResourceModule() {
        super(StatusResource.class, IndexResource.class, DocumentResource.class);
    }
}
