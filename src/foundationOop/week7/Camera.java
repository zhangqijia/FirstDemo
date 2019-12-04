package foundationOop.week7;

public class Camera {

    private double price;
    private String name;
    private String stockCode;

    /**
     * 0, new
     * 1, some signs of use
     * 2, obvious signs of use
     * 3, no guarantee
     */
    private int condition;

    public Camera() {
    }

    public Camera(double price, String name, String stockCode, int condition) {
        this.price = price;
        this.name = name;
        this.stockCode = stockCode;
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    private boolean vaildCondition() {
        return false;
    }

    private String getConditionString() {
        switch (condition) {
            case 0:
                return "new";
            case 1:
                return "some signs of use";
            case 2:
                return "obvious signs of use";
            case 3:
                return "no guarantee";
        }
        return "";
    }

    @Override
    public String toString() {
        return "Camera{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", condition=" + condition +
                '}';
    }

    public static void main(String[] args) {
        Camera camera = new Camera();

        boolean b = camera.vaildCondition();
    }
}
