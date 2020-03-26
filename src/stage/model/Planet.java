package stage.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Planet extends AstronomicalObject {
    private String name;
    private BigDecimal albedo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAlbedo() {
        return albedo;
    }

    public void setAlbedo(BigDecimal albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(name, planet.name) &&
                Objects.equals(RA, planet.RA) &&
                Objects.equals(declination, planet.declination) &&
                Objects.equals(magnitude, planet.magnitude) &&
                Objects.equals(distanceFromEarth, planet.distanceFromEarth) &&
                Objects.equals(albedo, planet.albedo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, RA, declination, magnitude, distanceFromEarth, albedo);
    }

    @Override
    public void setProperties(String[] properties) throws IllegalArgumentException {
        super.setProperties(properties);
        // extract properties from String and set them
        this.setName(properties[0].trim());
        this.setAlbedo(new BigDecimal(properties[5].trim()));
    }
}
