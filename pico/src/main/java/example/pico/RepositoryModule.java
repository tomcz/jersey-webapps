package example.pico;

import com.sun.jersey.spi.pico.container.servlet.BlankPicoConfiguration;
import example.domain.repositories.InMemoryDocumentRepository;
import org.picocontainer.MutablePicoContainer;

public class RepositoryModule extends BlankPicoConfiguration {

    @Override
    public void registerApplicationScope(MutablePicoContainer scope) {
        scope.addComponent(InMemoryDocumentRepository.class);
    }
}
