package foundationOop.week8;

import sheffield.*;

public class ComplexPlane {
    static final double XSCALE = 100.0;
    static final double YSCALE = 100.0;
    static final double DECAY = 0.02;

    /**
     * Write a programComplexPlane.java that generates curves using the
     * equation r =  {cos(p) +  i·sin(2p)}·exp(−DECAY*p) +0.01p
     * <p>
     * where  p  is a parameter and  d   is a constant value.
     * <p>
     * ComplexPlane.template.java(MOLE) provides the template for this task
     *
     * @param args
     */
    public static void main(String[] args) {
        EasyGraphics g = new EasyGraphics(250, 250, 125, 125);
        double p = 0.0;
        g.moveTo(XSCALE, 0);
        while (p < Math.PI * 16.0) {
            Complex z = new Complex(Math.cos(p), Math.sin(2.0 * p));
            Complex e = new Complex(Math.exp(-DECAY * p), 0.0);
//            return new Complex(realPart*c.getReal()-imagPart*c.getImag(),
//            realPart*c.getImag()+imagPart*c.getReal());

            Complex a = new Complex(0.01 * p, 0.0);

            // CODE MISSING
            Complex r = z.multiply(e).add(a);

            g.lineTo(r.getReal() * 5 , r.getImag() * 5);

            System.out.println("XSCALE:" + XSCALE);
            System.out.println("r.angel:" + r.angle());
            System.out.println("YSCALE:" + YSCALE * r.angle());
            p += 1;
        }
    }
}