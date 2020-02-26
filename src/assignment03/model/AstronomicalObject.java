package assignment03.model;

/**
 * USER: ZQJ
 * DATE: 2/26/2020
 * TIME: 12:35 PM
 */
public abstract class AstronomicalObject {

    protected Double RA;
    protected Double declination;
    protected Double magnitude;
    protected Double distanceFromEarth;

    /**
     * used to set AstronomicalObject's properties by the information in formatted String
     *
     * @param propertiesStr formatted String
     */
    public abstract void setProperties(String propertiesStr) throws IllegalArgumentException;

}
