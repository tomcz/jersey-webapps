package example.domain;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import org.joda.time.DateTime;

import java.util.Map;

public class Document {

    private Identity id;
    private Optional<String> value;

    private DateTime created;
    private DateTime updated;

    public Document() {
        this(new DateTime());
    }

    private Document(DateTime created) {
        this(new Identity(), Optional.<String>absent(), created, created);
    }

    private Document(Identity id, Optional<String> value, DateTime created, DateTime updated) {
        this.created = created;
        this.updated = updated;
        this.value = value;
        this.id = id;
    }

    public Identity getId() {
        return id;
    }

    public Document update(Optional<String> value) {
        return new Document(id, value, created, new DateTime());
    }

    public Map<String, String> toMap() {
        return ImmutableMap.of(
                "id", id.toString(),
                "value", value.or(""),
                "created", created.toString(),
                "updated", updated.toString());
    }
}
