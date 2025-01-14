package lld.carrental.exception;

public class CarNotAvailableException extends Exception {
    public CarNotAvailableException() {
        super("The car is not available");
    }
}
