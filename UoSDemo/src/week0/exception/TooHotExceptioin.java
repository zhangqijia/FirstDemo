package week0.exception;

public class TooHotExceptioin extends TempertureException {

    private int temperture;

    public TooHotExceptioin() {
    }

    public TooHotExceptioin(String message) {
        super(message);
    }

    public TooHotExceptioin(String message, int temperture) {
        super(message);
        this.temperture = temperture;
    }


}
