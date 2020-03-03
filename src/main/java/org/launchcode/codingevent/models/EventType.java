package org.launchcode.codingevent.models;

public enum EventType {
    CONFRENCE("Confrence"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");
    private final String displayName;
    public String getDisplayName() {
        return displayName;
    }


    EventType(String displayName) {
        this.displayName = displayName;
    }


}
