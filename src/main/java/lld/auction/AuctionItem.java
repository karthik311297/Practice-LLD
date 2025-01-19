package lld.auction;

import java.util.concurrent.TimeUnit;

public class AuctionItem {
    private long id;
    private final String name;
    private final String description;
    private final long startPrice;
    private final long duration;
    private final TimeUnit durationUnit;
    private AuctionStatus auctionStatus;

    public AuctionItem(String name, String description, long startPrice, long duration, TimeUnit durationUnit) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.duration = duration;
        this.durationUnit = durationUnit;
        this.auctionStatus = AuctionStatus.NOT_STARTED;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getStartPrice() {
        return startPrice;
    }

    public long getDuration() {
        return duration;
    }

    public TimeUnit getDurationUnit() {
        return durationUnit;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    @Override
    public String toString() {
        return "AuctionItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
