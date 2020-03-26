package stage.model;

import java.util.Objects;

public class Star extends AstronomicalObject {
    private Integer catalogueNumber;
    private String starType;
    private String constellation;

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
    public void setProperties(String[] properties) throws IllegalArgumentException {
        super.setProperties(properties);
        this.setCatalogueNumber(Integer.valueOf(properties[0].trim()));
        this.setStarType(properties[5].trim());
        this.setConstellation(properties[6].trim());
    }
}
