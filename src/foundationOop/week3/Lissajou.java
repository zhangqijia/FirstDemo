package foundationOop.week3;

import sheffield.EasyGraphics;

public class Lissajou {

    public static void main(String[] args) {
        EasyGraphics graphics = new EasyGraphics(3000, 3000);
        graphics.plot(1500, 1500);
        double x;
        double y;

        for (double t = 0; t < 3000; t+=10) {
            x = 250 * Math.sin(10 * t);
            y = 100 * Math.sin(5 * t + Math.PI / 6);
            graphics.lineTo(1500 + x, 1500 + y);
        }
    }
}
