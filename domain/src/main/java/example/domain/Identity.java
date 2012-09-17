package example.domain;

import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.UUID;

public final class Identity implements Serializable {

    public static final Identity NEW = new Identity("new");

    private final String value;

    public Identity() {
        value = UUID.randomUUID().toString();
    }

    private Identity(String value) {
        this.value = value;
    }

    public static Identity fromValue(String value) {
        if (Strings.isNullOrEmpty(value)) {
            throw new IllegalArgumentException("Cannot create Identity from null or empty value");
        }
        if (NEW.getValue().equalsIgnoreCase(value)) {
            return NEW;
        }
        return new Identity(value);
    }

    public boolean isNew() {
        return NEW.equals(this);
    }

    public String getValue() {
        return value;
    }

    public int hashCode() {
        return value.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Identity) {
            Identity other = (Identity) obj;
            return this.value.equals(other.value);
        }
        return false;
    }

    public String toString() {
        return getValue();
    }
}
