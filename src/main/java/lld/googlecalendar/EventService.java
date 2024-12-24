package lld.googlecalendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private final Map<String, Event> eventMap;

    private static final EventService instance = new EventService();

    private EventService() {
        this.eventMap = new HashMap<>();
    }

    public static EventService getInstance() {
        return instance;
    }

    public void createEvent(String id, String title, Slot slot, String organizerEmailId) {
        Event event = new Event
                .EventBuilder(id, title, slot, organizerEmailId)
                .build();
        eventMap.put(id, event);
    }

    public void createEvent(String id, String title, Slot slot,
                            String organizerEmailId, Location location, List<User> invitees) {
        Event event = new Event
                .EventBuilder(id, title, slot, organizerEmailId)
                .withLocation(location)
                .withInvitees(invitees)
                .build();
        for (User u : invitees) {
            u.inviteNotification(event.getId());
        }
        eventMap.put(id, event);
    }

    public Event getEvent(String id) {
        return eventMap.get(id);
    }

    public void respondToInvite(String eventId, String emailId, Response response) {
        Event e = getEvent(eventId);
        e.getInvitees().put(emailId, response);
    }

    public void showEventDetails(String eventId) {
        Event e = getEvent(eventId);
        System.out.println(e);

        if(e.getLocation() != null) {
            System.out.println("LOCATION : " + e.getLocation());
        }

        if(e.getInvitees()  != null) {
            for (Map.Entry<String, Response> entry : e.getInvitees().entrySet()) {
                String emailId = entry.getKey();
                Response response = entry.getValue();
                System.out.println("User " + emailId + ", Response " + response);
            }
        }
    }
}
