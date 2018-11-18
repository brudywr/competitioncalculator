package org.stas.demo;

/**
 * contains different types of competition events with their corresponding names
 * note that there can be not only decathlon related events but other ones
 */
public enum Event {
    SHORT_SPRINT_RACE("100m race"), LONG_JUMP("long jump"), SHORT_PUT("short put"), HIGH_JUMP("high jump"),
    LONG_SPRINT("400m race"), HURDLES_RACE("110m hurdles race"), DISCUS_THROW("discus throw"), POLE_VAULT("pole vault"),
    JAVELIN_THROW("javelin throw"), MIDDLE_RACE("1500m race");

    private String caption;

    Event(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return caption;
    }
}
