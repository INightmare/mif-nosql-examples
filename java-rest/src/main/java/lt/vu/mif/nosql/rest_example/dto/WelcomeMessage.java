package lt.vu.mif.nosql.rest_example.dto;

public class WelcomeMessage {
    private String message;

    public WelcomeMessage() {}

    public WelcomeMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}