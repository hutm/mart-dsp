package de.dfki.maths;

/**
 * (c) DFKI GmbH 26.9.2002
 * <p/>
 * Orginal C sources:
 * Author Mike Jackson - University of Edinburgh - 1999-2001
 * <p/>
 * The <code>Complex</code> class generates objects that represent
 * complex numbers in terms of real and imaginary components and
 * supports addition, subtraction, multiplication, scalar
 * multiplication and division or these numbers. The calculation of
 * complex conjugates, magnitude, phase and power (in decibels) of
 * the <code>Complex</code> numbers are also supported.
 *
 * @author Christoph Lauer
 * @version 1.0
 */
public class Complex implements Cloneable {

    /**
     * Constant required to calculate power values in dBs: log 10
     */
    protected static final float LOG10 = (float) Math.log(10);

    /**
     * Constant required to calculate power values in dBs: 20 / log
     * 10
     */
    protected static final float DBLOG = 20 / LOG10;

    /**
     * Real component
     */
    protected float real;

    /**
     * Imaginary component
     */
    protected float imag;

    /**
     * Create a new <code>Complex</code> number 0 + j0
     */
    public Complex() {
        real = imag = 0f;
    }

    /**
     * Create a new <code>Complex</code> number <code>real</code> +
     * j(<code>imag</code>)
     */
    public Complex(float real, float imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * Set the <code>Complex</code> number to be <code>real</code> +
     * j(<code>imag</code>)
     */
    public void setRealImag(float real, float imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * Get real component
     */
    public float getReal() {
        return real;
    }

    /**
     * Set real component
     */
    public void setReal(float real) {
        this.real = real;
    }

    /**
     * Get imaginary component
     */
    public float getImag() {
        return imag;
    }

    /**
     * Set imaginary component
     */
    public void setImag(float imag) {
        this.imag = imag;
    }

    /**
     * Add the given <code>Complex</code> number to this
     * <code>Complex</code> number
     */
    public Complex add (Complex complex) {
        return new Complex(real + complex.real, imag + complex.imag);
    }

    /**
     * Subtract the given <code>Complex</code> number from this
     * <code>Complex</code> number
     */
    public Complex subtract(Complex complex) {
        return new Complex(real - complex.real, imag - complex.imag);
    }

    /**
     * Multiply this <code>Complex</code> number by the given factor
     */
    public Complex multiply(float factor) {
        return new Complex(real * factor, imag * factor);
    }

    /**
     * Divide this <code>Complex</code> number by the given factor
     */
    public Complex divide(float factor) {
        return new Complex(real / factor, imag / factor);
    }

    public Complex divide(Complex divisor) {
        float a = real;
        float b = imag;
        float c = divisor.getReal();
        float d = divisor.getImag();
        float real = (a * c + b * d) / (c * c + d * d);
        float imag = (b * c - a * d) / (c * c + d * d);
        return new Complex(real, imag);
    }


    /**
     * Multiply this <code>Complex</code> number by the given
     * <code>Complex</code> number
     */
    public Complex multiply(Complex complex) {
        float nuReal = real * complex.real - imag * complex.imag;
        float nuImag = real * complex.imag + imag * complex.real;
        return new Complex(nuReal, nuImag);
    }

    /**
     * Set this <code>Complex</code> number to be its complex
     * conjugate
     */
    public Complex conjugate() {
        return new Complex(real, -imag);
    }



    /**
     * Return the magnitude of the <code>Complex</code> number
     */
    public float getMagnitude() {
        return magnitude(real, imag);
    }

    /**
     * Return the phase of the <code>Complex</code> number
     */
    public float getPhase() {
        return phase(real, imag);
    }

    /**
     * Return the power of this <code>Complex</code> number in dBs
     */
    public float getPower() {
        return power(real, imag);
    }

    /**
     * Add two <code>Complex</code> numbers: c = a + b
     */
    public static void add
            (Complex a, Complex b, Complex c) {
        c.real = a.real + b.real;
        c.imag = a.imag + b.imag;
    }

    /**
     * Subtract two <code>Complex</code> numbers: c = a - b
     */
    public static void subtract(Complex a, Complex b, Complex c) {
        c.real = a.real - b.real;
        c.imag = a.imag - b.imag;
    }

    /**
     * Multiply a <code>Complex</code> number by a factor: b = a *
     * factor
     */
    public static void multiply(Complex a, float factor, Complex b) {
        b.real = a.real * factor;
        b.imag = a.imag * factor;
    }

    /**
     * Divide a <code>Complex</code> number by a factor: b = a /
     * factor
     */
    public static void divide(Complex a, float factor, Complex b) {
        b.real = a.real / factor;
        b.imag = a.imag / factor;
    }

    /**
     * Multiply two <code>Complex</code> numbers: c = a * b
     */
    public static void multiply(Complex a, Complex b, Complex c) {
        c.real = a.real * b.real - a.imag * b.imag;
        c.imag = a.real * b.imag + a.imag * b.real;
    }

    /**
     * Place the <code>Complex</code> conjugate of a into b
     */
    public static void conjugate(Complex a, Complex b) {
        b.real = a.real;
        b.imag = -a.imag;
    }

    /**
     * Return the magnitude of a <code>Complex</code> number
     * <code>real</code> + (<code>imag</code>)j
     */
    public static float magnitude(float real, float imag) {
        return (float) Math.sqrt(real * real + imag * imag);
    }

    /**
     * Return the phase of a <code>Complex</code> number
     * <code>real</code> + (<code>imag</code>)j
     */
    public static float phase(float real, float imag) {
        return (float) Math.atan2(imag, real);
    }

    /**
     * Return the power of a <code>Complex</code> number
     * <code>real</code> + (<code>imag</code>)j
     */
    public static float power(float real, float imag) {
        return DBLOG * (float) Math.log(magnitude(real, imag));
    }


    /**
     * Place the phases (in radians) of the first <code>n</code>
     * elements of the array <code>complex</code> of
     * <code>Complex</code> numbers into the given
     * <code>phases</code> array
     */
    public static void phase(int n, Complex[] complex, float[] phases) {
        for (int i = 0; i < n; ++i) {
            phases[i] = complex[i].getPhase();
        }
    }

    /**
     * Return a clone of the <code>Complex</code> object
     */
    public Object clone() {
        return new Complex(real, imag);
    }
}
