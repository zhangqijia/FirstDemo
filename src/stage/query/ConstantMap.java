package stage.query;

import java.util.HashMap;
import java.util.Map;

/**
 * The relationship between Object properties and Query String
 *
 * @author ZQJ
 * @date 3/24/2020
 */
public class ConstantMap {
    private final static Map<String, String> CONSTANT_MAP = new HashMap<>();

    static {
        // Astronomical objects' name
        CONSTANT_MAP.put("planets", "stage.model.Planet");
        CONSTANT_MAP.put("stars", "stage.model.Star");
        CONSTANT_MAP.put("messiers", "stage.model.Messier");
        // properties' name
        CONSTANT_MAP.put("planets.name", "name");
        CONSTANT_MAP.put("planets.ra", "RA");
        CONSTANT_MAP.put("planets.decl", "declination");
        CONSTANT_MAP.put("planets.magn", "magnitude");
        CONSTANT_MAP.put("planets.distance", "distanceFromEarth");
        CONSTANT_MAP.put("planets.albedo", "albedo");
        CONSTANT_MAP.put("stars.number", "catalogueNumber");
        CONSTANT_MAP.put("stars.ra", "RA");
        CONSTANT_MAP.put("stars.decl", "declination");
        CONSTANT_MAP.put("stars.magn", "magnitude");
        CONSTANT_MAP.put("stars.distance", "distanceFromEarth");
        CONSTANT_MAP.put("stars.type", "starType");
        CONSTANT_MAP.put("stars.constellation", "constellation");
        CONSTANT_MAP.put("messiers.number", "catalogueNumber");
        CONSTANT_MAP.put("messiers.ra", "RA");
        CONSTANT_MAP.put("messiers.decl", "declination");
        CONSTANT_MAP.put("messiers.magn", "magnitude");
        CONSTANT_MAP.put("messiers.distance", "distanceFromEarth");
        CONSTANT_MAP.put("messiers.description", "description");
        // the symbols
        CONSTANT_MAP.put(">", ">");
        CONSTANT_MAP.put(">=", ">=");
        CONSTANT_MAP.put("=", "=");
        CONSTANT_MAP.put("!=", "!=");
        CONSTANT_MAP.put("<=", "<=");
        CONSTANT_MAP.put("<", "<");
    }

    public static String getProperty(String objectName) {
        return CONSTANT_MAP.get(objectName);
    }

    public static String getProperty(String objectName, String propertyName) {
        return CONSTANT_MAP.get(objectName + "." + propertyName);
    }

    /**
     * get symbol
     *
     * @param symbol symbol
     * @return symbol
     */
    public static String getSymbol(String symbol) {
        return CONSTANT_MAP.get(symbol);
    }
}
