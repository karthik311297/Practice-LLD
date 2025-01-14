package lld.carrental.exception;

public class NoCarFoundException extends Exception {

    public NoCarFoundException() {
        super("No Car found for search criteria");
    }
}

