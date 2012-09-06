package example.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import example.domain.DocumentRepository;
import example.domain.repositories.InMemoryDocumentRepository;

public class RepositoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DocumentRepository.class).to(InMemoryDocumentRepository.class).in(Singleton.class);
    }
}
