package myexceptions;

public class PolicyNotFoundException extends Exception {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}