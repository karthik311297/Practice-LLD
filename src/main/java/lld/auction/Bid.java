package lld.auction;

import lld.auction.user.User;

public class Bid implements Comparable<Bid>{
    private final long auctionItemId;
    private final long bidPrice;
    private final User user;

    public Bid(long auctionItemId, long bidPrice, User user) {
        this.auctionItemId = auctionItemId;
        this.bidPrice = bidPrice;
        this.user = user;
    }

    public long getAuctionItemId() {
        return auctionItemId;
    }

    public long getBidPrice() {
        return bidPrice;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int compareTo(Bid o) {
        return Long.compare(this.bidPrice, o.bidPrice);
    }
}
