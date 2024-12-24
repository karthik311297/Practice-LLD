package lld.googlecalendar;

import java.util.Arrays;

public class Demo {
    /*
    Design a calendar Application (similar like Google Calendar) -Ability to create, update, delete an Event

    An event would typically consist of {start, end, location, Owner, user-list, title}.
    Events can either be like meetings(with a dedicated location and appropriate guest-list) or as well be like holidays, birthdays, reminders etc.
    An event once created, can be either accepted or rejected by the constituent users - if neither it should be in neutral state.
    Get Calendar for a user Ui
    Get Event details.
    For a given set of users[U1, U2,....Un] identity a common free slot of time.
     */

    /*
        Location
            id
            name

        Event
            String id
            String Title
            Slot slot
            Location location
            String organizerEmailId
            Map<emailid, response> invitees

        EventBuilder

        User
            String emailid
            String name
            boolean SlotFree[24] *
            Map<eventid, Response> invitation


        UserService
            Map<emailid, User>
            create

        Response
            No Response
            Accept
            Decline

        EventService
            Map<id, Event>
            create
            respondToEvent(emailId, Response)

        Slot
            starttime
            endtime
     */

    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        EventService eventService = EventService.getInstance();

        userService.createUser("k1@com");
        userService.createUser("k2@com");
        userService.createUser("k3@com");
        userService.createUser("k4@com");

        eventService.createEvent("e1", "Do Exercise", new Slot(1, 2), "k1@com");
        eventService.createEvent("e2", "Meditate", new Slot(1, 2), "k2@com");
        eventService.createEvent("e3", "Design Discussion", new Slot(15, 17),
                "k3@com", new Location("room1"),
                Arrays.asList(userService.getUser("k1@com"), userService.getUser("k4@com")));
        eventService.createEvent("e4", "Arch Discussion", new Slot(12, 14),
                "k2@com", new Location("room2"),
                Arrays.asList(userService.getUser("k3@com"), userService.getUser("k4@com")));

        userService.getUser("k4@com").showInvites();
        eventService.showEventDetails("e1");
        eventService.showEventDetails("e2");
        eventService.showEventDetails("e3");
        eventService.showEventDetails("e4");

        userService.getUser("k4@com").respondToInvite("e3", Response.DECLINED);
        userService.getUser("k4@com").respondToInvite("e4", Response.ACCEPTED);

        eventService.showEventDetails("e3");
        eventService.showEventDetails("e4");
        userService.getUser("k4@com").showInvites();

    }
}
