package assignment03.model;

import java.util.Objects;

public class Planet extends AstronomicalObject {
    private String name;
    private Double albedo;

    public Planet() {
    }

    public Planet(String name, Double RA, Double declination, Double magnitude, Double distanceFromEarth, Double albedo) {
        this.name = name;
        this.RA = RA;
        this.declination = declination;
        this.magnitude = magnitude;
        this.distanceFromEarth = distanceFromEarth;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getAlbedo() {
        return albedo;
    }

    public void setAlbedo(Double albedo) {
        this.albedo = albedo;
    }

    @Override
    public void setProperties(String propertiesStr) throws IllegalArgumentException {
        // extract properties from String and set them
        String[] split = propertiesStr.split("\\|");
        this.setName(split[0].trim());
        this.setRA(Double.valueOf(split[1].trim()));
        this.setDeclination(Double.valueOf(split[2].trim()));
        this.setMagnitude(Double.valueOf(split[3].trim()));
        this.setDistanceFromEarth(Double.valueOf(split[4].trim()));
        this.setAlbedo(Double.valueOf(split[5].trim()));
    }
}
