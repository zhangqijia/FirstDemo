package week0.polymorphism;

import sheffield.EasyGraphics;
import sheffield2.Rectangle;
import sheffield2.Shape;

import java.util.ArrayList;

public class Picture {

    private ArrayList<Shape> shapes;

    public Picture(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void draw() {
        EasyGraphics easyGraphics = new EasyGraphics();
        shapes.forEach(shape -> {
            shape.draw(easyGraphics);
            System.out.println(shape);
        });
    }

    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        Rectangle rectangle = new Rectangle(100, 50);
        rectangle.setPosition(50, 10);
        shapes.add(rectangle);
        Square square = new Square(100);
        square.setPosition(100, 100);
        shapes.add(square);
        shapes.add(new Triangle(80));
        Picture picture = new Picture(shapes);
        picture.draw();
    }
}
