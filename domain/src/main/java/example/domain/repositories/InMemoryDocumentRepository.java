package example.domain.repositories;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import example.domain.Document;
import example.domain.DocumentRepository;
import example.domain.Identity;

import java.util.List;
import java.util.Map;

public class InMemoryDocumentRepository implements DocumentRepository {

    private final Map<Identity, Document> documents = Maps.newConcurrentMap();

    @Override
    public List<Identity> getIDs() {
        return Lists.newArrayList(documents.keySet());
    }

    @Override
    public Optional<Document> get(Identity id) {
        if (id.isNew()) {
            return Optional.of(new Document());
        }
        return Optional.fromNullable(documents.get(id));
    }

    @Override
    public void set(Document document) {
        documents.put(document.getId(), document);
    }
}
