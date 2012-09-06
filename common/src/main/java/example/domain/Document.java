package example.domain;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Document {

    private Identity id;
    private Optional<String> value;

    public Document() {
        this(new Identity(), Optional.<String>absent());
    }

    private Document(Identity id, Optional<String> value) {
        this.value = value;
        this.id = id;
    }

    public Identity getId() {
        return id;
    }

    public Document update(Optional<String> value) {
        return new Document(id, value);
    }

    public Map<String, String> toMap() {
        return ImmutableMap.of("id", id.toString(), "value", value.or(""));
    }
}
