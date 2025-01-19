package lld.auction;

import lld.auction.user.User;

import java.util.Set;

public class AuctionNotifier {

    public void notifyUsers(String notification, Set<User> users) {
        for (User user : users) {
            user.receiveNotification(notification);
        }
    }
}
