package com.adrianenciu.model;

import java.time.LocalDateTime;
import java.util.Map;

public class InternalEvent extends Event {

    private LocalDateTime created;

    public InternalEvent(String type, String status, Map<String, String> payload, LocalDateTime created) {
        super(type, status, payload);
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        InternalEvent that = (InternalEvent) o;

        return created != null ? created.equals(that.created) : that.created == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InternalEvent{" + super.toString() +
                "created=" + created;
    }
}
