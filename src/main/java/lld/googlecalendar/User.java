package lld.googlecalendar;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String emailId;

    private Map<String, Response> invitationMap;

    public User(String emailId) {
        this.emailId = emailId;
        this.invitationMap = new HashMap<>();
    }

    public Map<String, Response> getInvitationMap() {
        return invitationMap;
    }

    public String getEmailId() {
        return emailId;
    }

    public void inviteNotification(String eventId)
    {
        invitationMap.put(eventId, Response.NO_RESPONSE);
    }

    public void showInvites()
    {
        for(Map.Entry<String, Response> entry : invitationMap.entrySet())
        {
            String eventId = entry.getKey();
            Response response = entry.getValue();
            System.out.println("Event " + eventId + ", Response " + response);
        }
    }

    public void respondToInvite(String eventId, Response response)
    {
        invitationMap.put(eventId, response);
        EventService.getInstance()
                .respondToInvite(eventId, emailId, response);
    }
}
