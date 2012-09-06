package example.guice;

import com.google.inject.AbstractModule;
import example.domain.resources.DocumentResource;
import example.domain.resources.IndexResource;
import example.domain.resources.StatusResource;

public class ResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StatusResource.class);
        bind(IndexResource.class);
        bind(DocumentResource.class);
    }
}
