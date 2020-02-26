package week0.exception;

public class CupCoffee {



    private int content;
    private int temperture;

    public CupCoffee(int content, int temperture) {
        this.content = content;
        this.temperture = temperture;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getTemperture() {
        return temperture;
    }

    public void setTemperture(int temperture) {
        this.temperture = temperture;
    }
}
