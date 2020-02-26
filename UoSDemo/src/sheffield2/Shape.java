package sheffield2;

import sheffield.EasyGraphics;

public abstract class Shape {

    protected double x;
    protected double y;

    public void setPosition(double xval, double yval) {
        x = xval;
        y = yval;
    }

    public abstract void draw(EasyGraphics g);

    public abstract double area();

    @Override
    public String toString() {
        return "Shape{ area of " + getClass().getTypeName() +
                " is " +
                area()
                + '}';
    }
}
