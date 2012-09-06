package example.domain;

import com.google.common.base.Optional;

import java.util.List;

public interface DocumentRepository {

    List<Identity> getIDs();

    Optional<Document> get(Identity id);

    void set(Document document);
}
