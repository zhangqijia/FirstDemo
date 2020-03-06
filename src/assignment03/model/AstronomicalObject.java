package assignment03.model;

import java.math.BigDecimal;

/**
 * USER: ZQJ
 * DATE: 2/26/2020
 * TIME: 12:35 PM
 */
public class AstronomicalObject {

    protected BigDecimal RA;
    protected BigDecimal declination;
    protected BigDecimal magnitude;
    protected BigDecimal distanceFromEarth;

    public BigDecimal getRA() {
        return RA;
    }

    public void setRA(BigDecimal RA) {
        this.RA = RA;
    }

    public BigDecimal getDeclination() {
        return declination;
    }

    public void setDeclination(BigDecimal declination) {
        this.declination = declination;
    }

    public BigDecimal getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(BigDecimal magnitude) {
        this.magnitude = magnitude;
    }

    public BigDecimal getDistanceFromEarth() {
        return distanceFromEarth;
    }

    public void setDistanceFromEarth(BigDecimal distanceFromEarth) {
        this.distanceFromEarth = distanceFromEarth;
    }

    /**
     * used to set AstronomicalObject's properties by the information in formatted String
     *
     * @param properties formatted String
     */
    public void setProperties(String[] properties) throws IllegalArgumentException {
        this.setRA(new BigDecimal(properties[1].trim()));
        this.setDeclination(new BigDecimal(properties[2].trim()));
        this.setMagnitude(new BigDecimal(properties[3].trim()));
        this.setDistanceFromEarth(new BigDecimal(properties[4].trim()));
    }
}
