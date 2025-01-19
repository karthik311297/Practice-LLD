package lld.auction.exception;

public class AuctionAlreadyCompletedException extends Exception{
    public AuctionAlreadyCompletedException(String message) {
        super(message);
    }
}
