package lld.googlecalendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {

    private String id;
    private String title;
    private Slot slot;
    private Location location;
    private String organizerEmailId;
    private Map<String, Response> invitees;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Slot getSlot() {
        return slot;
    }

    public Location getLocation() {
        return location;
    }

    public String getOrganizerEmailId() {
        return organizerEmailId;
    }

    public Map<String, Response> getInvitees() {
        return invitees;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOrganizerEmailId(String organizerEmailId) {
        this.organizerEmailId = organizerEmailId;
    }

    public void setInvitees(Map<String, Response> invitees) {
        this.invitees = invitees;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", slot=" + slot +
                ", organizerEmailId='" + organizerEmailId + '\'' +
                '}';
    }

    public static class EventBuilder {
        private Event event;

        public EventBuilder(String id, String title, Slot slot, String organizerEmailId) {
            event = new Event();
            event.setId(id);
            event.setTitle(title);
            event.setSlot(slot);
            event.setOrganizerEmailId(organizerEmailId);
        }

        public EventBuilder withLocation(Location location) {
            event.setLocation(location);
            return this;
        }

        public EventBuilder withInvitees(List<User> invitees) {
            Map<String, Response> inviteesResponseMap = new HashMap<>();

            for (User u : invitees) {
                inviteesResponseMap.put(u.getEmailId(), Response.NO_RESPONSE);
            }
            event.setInvitees(inviteesResponseMap);
            return this;
        }

        public Event build() {
            return event;
        }
    }
}
