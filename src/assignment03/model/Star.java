package assignment03.model;

import java.util.Objects;

public class Star extends AstronomicalObject {
    private Integer catalogueNumber;
    private String starType;
    private String constellation;

    public Star(Integer catalogueNumber, Double RA, Double declination, Double magnitude, Double distanceFromEarth, String starType, String constellation) {
        this.catalogueNumber = catalogueNumber;
        this.RA = RA;
        this.declination = declination;
        this.magnitude = magnitude;
        this.distanceFromEarth = distanceFromEarth;
        this.starType = starType;
        this.constellation = constellation;
    }

    public Star() {
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public Integer getCatalogueNumber() {
        return catalogueNumber;
    }

    public void setCatalogueNumber(Integer catalogueNumber) {
        this.catalogueNumber = catalogueNumber;
    }

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

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Objects.equals(catalogueNumber, star.catalogueNumber) &&
                Objects.equals(RA, star.RA) &&
                Objects.equals(declination, star.declination) &&
                Objects.equals(magnitude, star.magnitude) &&
                Objects.equals(distanceFromEarth, star.distanceFromEarth) &&
                Objects.equals(starType, star.starType) &&
                Objects.equals(constellation, star.constellation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogueNumber, RA, declination, magnitude, distanceFromEarth, starType, constellation);
    }


    @Override
    public void setProperties(String propertiesStr) throws IllegalArgumentException {
        // extract properties from String and set them
        String[] split = propertiesStr.split("\\|");
        this.setCatalogueNumber(Integer.valueOf(split[0].trim()));
        this.setRA(Double.valueOf(split[1].trim()));
        this.setDeclination(Double.valueOf(split[2].trim()));
        this.setMagnitude(Double.valueOf(split[3].trim()));
        this.setDistanceFromEarth(Double.valueOf(split[4].trim()));
        this.setStarType(split[5].trim());
        this.setConstellation(split[6].trim());
    }
}
