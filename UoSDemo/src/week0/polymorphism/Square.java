package week0.polymorphism;

import sheffield.EasyGraphics;
import sheffield2.Rectangle;

public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    public static void main(String[] args) {
        Square square = new Square(100);
        square.draw(new EasyGraphics());
    }
}
