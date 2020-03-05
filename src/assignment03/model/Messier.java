package assignment03.model;

import java.util.Objects;

public class Messier extends AstronomicalObject {

    private Integer catalogueNumber;
    private String constellation;
    private String description;

    public Integer getCatalogueNumber() {
        return catalogueNumber;
    }

    public void setCatalogueNumber(Integer catalogueNumber) {
        this.catalogueNumber = catalogueNumber;
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
            super.setProperties(properties);
            // extract properties from String and set them
            this.setCatalogueNumber(Integer.valueOf(properties[0].trim()));
            this.setConstellation(properties[5].trim());
            // description might be null;
            if (properties.length < 7) {
                description = null;
            } else {
                description = properties[6];
            }
            if (description == null)
                description = "";
            this.setDescription(description.trim());
    }
}
