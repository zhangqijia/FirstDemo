package assignment03.model;

/**
 * USER: ZQJ
 * DATE: 2/26/2020
 * TIME: 12:35 PM
 */
public class AstronomicalObject {

    protected Double RA;
    protected Double declination;
    protected Double magnitude;
    protected Double distanceFromEarth;

    public Double getRA() {
        return RA;
    }

    public void setRA(Double RA) {
        this.RA = RA;
    }

    public Double getDeclination() {
        return declination;
    }

    public void setDeclination(Double declination) {
        this.declination = declination;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Double getDistanceFromEarth() {
        return distanceFromEarth;
    }

    public void setDistanceFromEarth(Double distanceFromEarth) {
        this.distanceFromEarth = distanceFromEarth;
    }

    /**
     * used to set AstronomicalObject's properties by the information in formatted String
     *
     * @param properties formatted String
     */
    public void setProperties(String[] properties) throws IllegalArgumentException {
        this.setRA(Double.valueOf(properties[1].trim()));
        this.setDeclination(Double.valueOf(properties[2].trim()));
        this.setMagnitude(Double.valueOf(properties[3].trim()));
        this.setDistanceFromEarth(Double.valueOf(properties[4].trim()));
    }

    ;

}
