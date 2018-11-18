package org.stas.demo;

import java.util.HashMap;
import java.util.Map;

import static org.stas.demo.Event.*;

public enum CompetitionType {
    DECATHLON("Men's decathlon"), DECATHLON_FEMALE("Women's decathlon");

    static private Map<CompetitionType, Event[]> eventsOrder = new HashMap<>();

    static {
        eventsOrder.put(DECATHLON, new Event[]{SHORT_SPRINT_RACE, LONG_JUMP, SHORT_PUT, HIGH_JUMP, LONG_SPRINT,
                HURDLES_RACE, DISCUS_THROW, POLE_VAULT, JAVELIN_THROW, MIDDLE_RACE});
        eventsOrder.put(DECATHLON_FEMALE, new Event[]{SHORT_SPRINT_RACE, DISCUS_THROW, POLE_VAULT, JAVELIN_THROW,
                LONG_SPRINT, HURDLES_RACE, LONG_JUMP, SHORT_PUT, HIGH_JUMP, MIDDLE_RACE});
    }

    private String caption;

    CompetitionType(String caption) {
        this.caption = caption;
    }

    public static Event[] getEventsOrder(CompetitionType competitionType) {
        return eventsOrder.get(competitionType);
    }

    @Override
    public String toString() {
        return caption;
    }
}
