package stage.query;

/**
 * @author ZQJ
 * @date 3/24/2020
 */
public class Criterion {
    private String key;
    private String symbol;
    private String value;

    public Criterion(String key, String symbol, String value) {
        this.key = key;
        this.symbol = symbol;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
