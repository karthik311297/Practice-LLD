package lld.auction;

import lld.auction.user.User;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AuctionStopScheduler {

    private final AuctionNotifier auctionNotifier;

    private final ScheduledExecutorService scheduledExecutorService;

    public AuctionStopScheduler(AuctionNotifier auctionNotifier) {
        this.auctionNotifier = auctionNotifier;
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    public void scheduleAuctionStoppage(Long auctionId, String auctionStoppageMessage, long afterDuration, TimeUnit timeUnit) {
        scheduledExecutorService.schedule(() ->
        {
            AuctionRepository auctionRepository = AuctionRepository.getInstance();
            AuctionController auctionController = AuctionController.getInstance();
            Set<User> bidders = auctionController.getAuctionItemBidders(auctionId);
            auctionRepository.updateAuctionStatus(auctionId, AuctionStatus.COMPLETED);
            if(bidders.isEmpty()) return;
            Bid highest = auctionController.getCurrentHighestBidderForAuction(auctionId);
            User winner = highest.getUser();
            auctionNotifier.notifyUsers(String.format(auctionStoppageMessage, highest.getBidPrice(), winner),
                    bidders);
        }, afterDuration, timeUnit);
        scheduledExecutorService.shutdown();
    }
}
