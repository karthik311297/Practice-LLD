package lld.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuctionRepository {

    private long seqId;
    private final Map<Long, AuctionItem> auctionItemMap;
    private static final AuctionRepository instance = new AuctionRepository();

    private AuctionRepository() {
        this.auctionItemMap = new HashMap<>();
        this.seqId = 1;
    }

    public static AuctionRepository getInstance() {
        return instance;
    }

    public synchronized long createAuctionListing(AuctionItem auctionItem) {
        auctionItem.setId(seqId);
        auctionItemMap.put(seqId, auctionItem);
        ++seqId;
        return auctionItem.getId();
    }

    public synchronized AuctionItem getAuctionItemById(long id) {
        return auctionItemMap.get(id);
    }

    public List<AuctionItem> getAllActiveAuctions()
    {
        return auctionItemMap.values()
                .stream()
                .filter(auctionItem -> auctionItem.getAuctionStatus() == AuctionStatus.RUNNING).toList();
    }

    public synchronized void updateAuctionStatus(long auctionId, AuctionStatus auctionStatus) {
        AuctionItem auctionItem = auctionItemMap.get(auctionId);
        auctionItem.setAuctionStatus(auctionStatus);
        auctionItemMap.put(auctionId, auctionItem);
    }
}
