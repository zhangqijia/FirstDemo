package foundationOop.week8;

/**
 * A class for representing complex numbers
 * Written by: Guy J. Brown
 * First written: 7/9/98
 * Last rewritten: 26/10/02
 * Cleaned up: 31/08/06 by YG
 */

import foundationOop.assignment03.sheffield.EasyReader;

public class Complex {

    // instance variables
    double realPart;
    double imagPart;

    // constructor methods

    /**
     * Creates an instance of the Complex class with default values
     * realPart = 0.0, imagPart = 0.0
     */
    public Complex() {
        realPart = 0.0;
        imagPart = 0.0;
    }

    /**
     * Creates an instance of the Complex class with specified values
     * @param r real part
     * @param i imaginary part
     */
    public Complex(double r, double i) {
        realPart = r;
        imagPart = i;
    }

    // accessor methods

    /**
     * returns the real part of the complex number
     * @return the real part of the complex number
     */
    public double getReal() {
        return realPart;
    }

    /**
     * returns the imaginary part of the complex number
     * @return the imaginary part of the complex number
     */
    public double getImag() {
        return imagPart;
    }

    // mutator methods

    /**
     * sets the real and imaginary parts of a complex number
     * @param r real part
     * @param i imaginary part
     */
    public void set(double r, double i) {
        realPart = r;
        imagPart = i;
    }

    /**
     * sets the real and imaginary parts of a complex number
     * @param r real part
     */
    public void setReal(double r) {
        realPart = r;
    }

    /**
     * sets the real and imaginary parts of a complex number
     * @param i imaginary part
     */
    public void setImag(double i) {
        imagPart = i;
    }

    // mathematical operations

    /**
     * returns the sum of two complex numbers
     * @param c the complex number to add
     * @return the sum of two complex numbers
     */
    public Complex add(Complex c) {
        return new Complex(realPart+c.getReal(), imagPart+c.getImag());
    }

    /**
     * returns the difference of two complex numbers
     * @param c the complex number to subtract
     * @return the difference of two complex numbers
     */
    public Complex subtract(Complex c) {
        return new Complex(realPart-c.getReal(), imagPart-c.getImag());
    }

    /**
     * returns the product of two complex numbers
     * @param c the complex number to multiply by
     * @return the product of two complex numbers
     */
    public Complex multiply(Complex c) {
        return new Complex(realPart*c.getReal()-imagPart*c.getImag(),
                realPart*c.getImag()+imagPart*c.getReal());
    }

    /**
     * returns the division of two complex numbers
     * @param c the complex number to divide by
     * @return the division of two complex numbers
     */
    public Complex divide(Complex c) {
        double d = c.getReal()*c.getReal()+c.getImag()*c.getImag();
        return new Complex((realPart*c.getReal()+imagPart*c.getImag())/d,
                (realPart*c.getReal()-realPart*c.getImag())/d);
    }

    /**
     * returns the complex conjugate
     * @return the complex conjugate
     */
    public Complex conj() {
        return new Complex(realPart, -imagPart);
    }

    /**
     * returns the magnitude (modulus) of a complex number
     * @return the magnitude (modulus)
     */
    public double abs() {
        return Math.sqrt(realPart*realPart+imagPart*imagPart);
    }

    /**
     * computes the argument (angle) of a complex number
     * @return the argument (angle)
     */
    public double angle() {
        return Math.atan(imagPart/realPart);
    }

    // miscellaneous methods

    /**
     * returns a copy of a complex number
     * @return a copy of a complex number
     */
    public Complex copy() {
        return new Complex(realPart, imagPart);
    }

    /**
     * converts a complex number to a string for display
     * @return a a string representing the complex number
     */
    public String toString() {
        if (imagPart<0.0)
            return (realPart + (imagPart + "i"));
        else
            return (realPart + "+" + imagPart + "i");
    }

    // main method - test harness
    public static void main(String[] args) {
        EasyReader keyboard = new EasyReader();
        String reply;

        do {
            // create two complex numbers
            System.out.println("First number: ");
            double realPart = keyboard.readDouble("Enter real part: ");
            double imagPart = keyboard.readDouble("Enter imaginary part: ");
            Complex c1 = new Complex(realPart, imagPart);
            System.out.println("Second number: ");
            realPart = keyboard.readDouble("Enter real part: ");
            imagPart = keyboard.readDouble("Enter imaginary part: ");
            Complex c2 = new Complex(realPart, imagPart);

            // display the two numbers
            System.out.println();
            System.out.println("c1 = " + c1);
            System.out.println("c2 = " + c2);

            // test the accessor and mutator methods
            System.out.println();
            System.out.println("real part of c1 = " + c1.getReal());
            System.out.println("real part of c2 = " + c2.getReal());
            System.out.println("imaginary part of c1 = " + c1.getImag());
            System.out.println("imaginary part of c2 = " + c2.getImag());

            // test the operations on the numbers
            System.out.println();
            System.out.println("c1+c2 = " + c1.add(c2));
            System.out.println("c1-c2 = " + c1.subtract(c2));
            System.out.println("c1*c2 = " + c1.multiply(c2));
            System.out.println("c1/c2 = " + c1.divide(c2));
            System.out.println("abs(c1) = " + c1.abs());
            System.out.println("abs(c2) = " + c2.abs());
            System.out.println("conj(c1) = " + c1.conj());
            System.out.println("conj(c2) = " + c2.conj());
            System.out.println("angle(c1) = " + c1.angle());
            System.out.println("angle(c2) = " + c2.angle());

            // another go?
            System.out.println();
            reply = keyboard.readString("Another go (y/n)?: ");
        } while (reply.equals("y"));
    }
}