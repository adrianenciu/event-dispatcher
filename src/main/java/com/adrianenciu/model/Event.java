package com.adrianenciu.model;

import java.util.Map;

public class Event {
    private String type;
    private String status;
    private Map<String, String> payload;

    public Event() {
    }

    public Event(String type, String status, Map<String, String> payload) {
        this.type = type;
        this.status = status;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, String> payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (type != null ? !type.equals(event.type) : event.type != null) return false;
        if (status != null ? !status.equals(event.status) : event.status != null) return false;
        return payload != null ? payload.equals(event.payload) : event.payload == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", payload=" + payload +
                '}';
    }
}
