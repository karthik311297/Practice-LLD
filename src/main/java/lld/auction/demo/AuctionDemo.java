package lld.auction.demo;

import lld.auction.AuctionController;
import lld.auction.AuctionItem;
import lld.auction.AuctionRepository;
import lld.auction.Bid;
import lld.auction.user.User;

import java.util.concurrent.TimeUnit;

public class AuctionDemo {
    /*
    Auction System -

    This system allows for the creation and management of auctions, user participation in bidding, and handling transactions.


    The online auction system should allow users to register and log in to their accounts.
    Users should be able to create new auction listings with details such as item name, description, starting price, and auction duration.
    Users should be able to browse and search for auction listings based on various criteria (e.g., item name, category, price range).
    Users should be able to place bids on active auction listings.
    The system should automatically update the current highest bid and notify the bidders accordingly.
    The auction should end when the specified duration is reached, and the highest bidder should be declared the winner.
    The system should handle concurrent access to auction listings and ensure data consistency.
    The system should be extensible to accommodate future enhancements and new features.

     */

    /*
        User
            id
            name
            receiveNotification()

        AuctionItem
            id
            name
            description
            long startPrice
            LocalDateTime duration
            AuctionStatus

        AuctionStatus
            NOT_STARTED
            STARTED
            COMPLETED

        Bid
            auction item id
            long bidPrice
            User

        SearchFilter
            name based, price based

        Search

        AuctionRepository
            Map<auction item id, AuctionItem>
            createAuctionListing(AuctionItem)
            getAuctionItemById()
            updateAuctionStatus

        AuctionController
            AuctionStopScheduler
            Map<auction item id, highestBidTillNow>
            Map<auction item id, startTime>
            Map<auction item id, List<User>> bidders
            startAuctionForItem(auction item id)
            submitBidForAuctionItem(auction item id, bidPrice, User)
                notifyOtherBiddersIfHighestBidderChanged
            getCurrentHighestBidderForAuctionItem(auction item id)

        AuctionStopScheduler
            scheduleAuctionToBeStoppedAtTime(AuctionItem, bidders)
                notifyBiddersOfWinner

        AuctionNotifier
            notifyUsers(String notification, List<User>)

        AuctionAlreadyCompletedException

        AuctionNotStartedException


     */

    public static void main(String[] args) throws Exception {
        AuctionRepository auctionRepository = AuctionRepository.getInstance();
        long auctionId = auctionRepository
                .createAuctionListing(new AuctionItem("Pokemon Cards", "Cards from indigo league", 200, 15, TimeUnit.SECONDS));
        AuctionController auctionController = AuctionController.getInstance();
        auctionController.startAuctionForItem(auctionId);
        System.out.println(auctionRepository.getAllActiveAuctions().size());
        auctionController.submitBidForAuctionItem(auctionId, new Bid(auctionId, 300, new User("1", "Karthik")));
        Thread.sleep(5000);
        auctionController.submitBidForAuctionItem(auctionId, new Bid(auctionId, 500, new User("2", "Ash")));
        Thread.sleep(5000);
        auctionController.submitBidForAuctionItem(auctionId, new Bid(auctionId, 600, new User("3", "Brock")));
        auctionController.submitBidForAuctionItem(auctionId, new Bid(auctionId, 500, new User("5", "Gary")));
        Thread.sleep(9000);
        try {
            // Testing auction already completed
            auctionController.submitBidForAuctionItem(auctionId, new Bid(auctionId, 700, new User("4", "Misty")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(auctionRepository.getAllActiveAuctions().size());
    }


}
