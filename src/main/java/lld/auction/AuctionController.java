package lld.auction;

import lld.auction.exception.AuctionAlreadyCompletedException;
import lld.auction.exception.AuctionNotStartedException;
import lld.auction.user.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuctionController {
    private final AuctionStopScheduler auctionStopScheduler;
    private final AuctionNotifier auctionNotifier;
    private final Map<Long, Bid> highestBidForAuctionItem;
    private final Map<Long, Set<User>> auctionItemBidders;
    private static final AuctionController instance = new AuctionController();

    private AuctionController() {
        this.auctionNotifier = new AuctionNotifier();
        this.highestBidForAuctionItem = new HashMap<>();
        this.auctionItemBidders = new HashMap<>();
        this.auctionStopScheduler = new AuctionStopScheduler(auctionNotifier);
    }

    public static AuctionController getInstance() {
        return instance;
    }

    public void startAuctionForItem(Long auctionId) {
        AuctionRepository auctionRepository = AuctionRepository
                .getInstance();
        AuctionItem auctionItem = auctionRepository.getAuctionItemById(auctionId);
        auctionRepository
                .updateAuctionStatus(auctionId, AuctionStatus.RUNNING);
        auctionItemBidders.put(auctionId, new HashSet<>());
        auctionStopScheduler.scheduleAuctionStoppage(auctionId, "The highest bid for auction item "
                + auctionItem + ", is %s." + " And the winner is %s", auctionItem.getDuration(), auctionItem.getDurationUnit());
    }

    public void submitBidForAuctionItem(Long auctionId, Bid bid) throws AuctionNotStartedException, AuctionAlreadyCompletedException {
        AuctionRepository auctionRepository = AuctionRepository
                .getInstance();
        AuctionItem auctionItem = auctionRepository.getAuctionItemById(auctionId);
        if (auctionItem.getAuctionStatus() == AuctionStatus.NOT_STARTED)
            throw new AuctionNotStartedException("The auction is not yet accepting bids");
        if (auctionItem.getAuctionStatus() == AuctionStatus.COMPLETED)
            throw new AuctionAlreadyCompletedException("The auction is already completed");
        synchronized (this) {
            auctionItemBidders.get(auctionId).add(bid.getUser());
            updateHighestBid(bid, auctionItem);
        }
    }

    private void updateHighestBid(Bid newBid, AuctionItem auctionItem) {
        Long auctionId = auctionItem.getId();
        Set<User> auctionBidders = auctionItemBidders.get(auctionId);
        Bid currentHighestBid = highestBidForAuctionItem.get(auctionId);
        if (currentHighestBid == null || currentHighestBid.compareTo(newBid) < 0) {
            synchronized (this) {
                currentHighestBid = highestBidForAuctionItem.get(auctionId);
                if (currentHighestBid == null || currentHighestBid.compareTo(newBid) < 0) {
                    highestBidForAuctionItem.put(auctionId, newBid);
                    Bid highest = getCurrentHighestBidderForAuction(auctionId);
                    auctionNotifier.notifyUsers("The current highest bid for auction item "
                            + auctionItem + ", is " + highest.getBidPrice() + ", submitted by user : " + highest.getUser(), auctionBidders);
                }
            }
        }
    }

    public Bid getCurrentHighestBidderForAuction(Long auctionId) {
        return highestBidForAuctionItem.get(auctionId);
    }

    public Set<User> getAuctionItemBidders(Long auctionId) {
        return auctionItemBidders.get(auctionId);
    }
}
