package assignment03.model;

import java.util.Objects;

public class Messier extends AstronomicalObject {

    private Integer catalogueNumber;
    private String constellation;
    private String description;

    public Messier() {
    }

    public Messier(Integer catalogueNumber, Double RA, Double declination, Double magnitude, Double distanceFromEarth, String constellation, String description) {
        this.catalogueNumber = catalogueNumber;
        this.RA = RA;
        this.declination = declination;
        this.magnitude = magnitude;
        this.distanceFromEarth = distanceFromEarth;
        this.constellation = constellation;
        this.description = description;
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

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messier messier = (Messier) o;
        return Objects.equals(catalogueNumber, messier.catalogueNumber) &&
                Objects.equals(RA, messier.RA) &&
                Objects.equals(declination, messier.declination) &&
                Objects.equals(magnitude, messier.magnitude) &&
                Objects.equals(distanceFromEarth, messier.distanceFromEarth) &&
                Objects.equals(constellation, messier.constellation) &&
                Objects.equals(description, messier.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogueNumber, RA, declination, magnitude, distanceFromEarth, constellation, description);
    }

    @Override
    public void setProperties(String[] properties) throws IllegalArgumentException {
        // extract properties from String and set them
        this.setCatalogueNumber(Integer.valueOf(properties[0].trim()));
        this.setConstellation(properties[5].trim());
        // description might be null;
        String description = properties[6];
        if (description == null)
            description = "";
        this.setDescription(description.trim());
    }
}
