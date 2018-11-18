package org.stas.demo;

import java.util.Map;

public class RawResultEntry {
    private String name;
    private Map<Event, Double> events;

    public RawResultEntry(String name, Map<Event, Double> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public Map<Event, Double> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name: ").append(name)
                .append(", events: ").append(events);

        return stringBuilder.toString();

    }
}
