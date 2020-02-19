package week2;

import sheffield.EasyGraphics;

public class DrawRecursion {

    public static void drawSquare(EasyGraphics easyGraphics, int x, int y, int side) {
        int newSide = side / 2;
        int x1 = x - newSide;
        int x2 = x + newSide;
        int y1 = y - newSide;
        int y2 = y + newSide;
        easyGraphics.moveTo(x1, y1);
        easyGraphics.lineTo(x1, y2);
        easyGraphics.lineTo(x2, y2);
        easyGraphics.lineTo(x2, y1);
        easyGraphics.lineTo(x1, y1);
        if (newSide > 3) {
            drawSquare(easyGraphics, x1, y1, newSide);
            drawSquare(easyGraphics, x2, y1, newSide);
            drawSquare(easyGraphics, x2, y2, newSide);
            drawSquare(easyGraphics, x1, y2, newSide);

        }
    }

    public static void drawTriangle(EasyGraphics easyGraphics, double x, double y, double side) {
        double newSide = side / 2;
        double x1 = x - newSide;
        double x2 = x + newSide;
        double y1 = y - newSide;
        double y2 = y + newSide;
        easyGraphics.moveTo(x1, y1);
        easyGraphics.lineTo(x, y2);
        easyGraphics.lineTo(x2, y1);
        easyGraphics.lineTo(x1, y1);
        if (newSide > 4) {
            drawTriangle(easyGraphics, x1, y1, newSide);
            drawTriangle(easyGraphics, x2, y1, newSide);
            drawTriangle(easyGraphics, x, y2, newSide);
        }
    }


    public static void main(String[] args) {
//        drawSquare(new EasyGraphics(500, 500), 250, 250, 96);
        drawTriangle(new EasyGraphics(500, 500), 250, 250, 120);
    }
}
