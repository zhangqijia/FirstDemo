package week0.polymorphism;

import sheffield.EasyGraphics;
import sheffield2.Shape;

public class Triangle extends Shape {
    private double side;

    public Triangle(double side) {
        this.side = side;
    }

    @Override
    public void draw(EasyGraphics g) {
        g.moveTo(0, 0);
        g.lineTo(x + side, y);
        double high = Math.sqrt(side * side * 3 / 4);
        g.lineTo(x + side / 2, y + high);
        g.lineTo(x, y);
    }

    @Override
    public double area() {
        return side * side * Math.sqrt(3) / 4;
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle(80);
        triangle.setPosition(0,0);
        EasyGraphics easyGraphics = new EasyGraphics(200, 200);
        triangle.draw(easyGraphics);
        double area = triangle.area();
        System.out.println("the area of equilateral triangle: " + area);
    }
}
