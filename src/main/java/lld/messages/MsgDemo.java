package lld.messages;

public class MsgDemo {
    /*
        Design classes for a system that sends different types of messages - Email, SMS, Whatsapp
     */

    /*
        enum MessageType
            EMAIL, SMS, WHATSAPP

        Message (Abstract class)
            MessageType
        implemented by :
            EmailMessage  - from email-id, list<to-email-id>, subject, content  -> constructor will call super(MessageType.EMAIL)
            WhatsappMessage - from number, list<to-number>, text -> constructor will call super(MessageType.WHATSAPP)
            SMSMessage - from number, list<to-number>, text -> constructor will call super(MessageType.SMS)

        MessageChannel (abstract)
            protected ChannelSubscriptionManager;
            sendMessage(Message message)
        implemented by
            EmailChannel, SMSChannel, WhatsappChannel

        RegisteredMessageChannels
            getMessageChannelByMessageType(MessageType)

        ChannelSubscriptionManager
            Map<MessageType, List<SubscriptionDetail>>
            subscribeToChannel()
            getSubscribers(MessageType)

        SubscriptionDetail - can be phone number, email-id, etc or anything else as required
            get()

        MessageSender
            SendMessage(Message) - gets the message channel from registered channels based on type and calls the channel to sends message
                (here message is passed like -
                    new EmailMessage(subject, content);
                    new WhatsappMessage(text);
                    new SMSMessage(text);)

     */
}
