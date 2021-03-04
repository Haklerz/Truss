package com.haklerz.multiplayer;

public class Vec {
    private static final double TWO_PI = 2 * Math.PI;

    public double x, y;

    /**
     * Creates a new vector with components equal to the scalars x and y.
     * 
     * @param x the x component
     * @param y the y component
     */
    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector with components equal to the components of the vector v.
     * <p>
     * If v is <code>null</code> a vector with both components equal to zero will be
     * created.
     * 
     * @param v the vector v
     */
    public Vec(Vec v) {
        this((v != null) ? v.x : 0, (v != null) ? v.y : 0);
    }

    /**
     * Returns a new vector with magnitude m and angle a.
     * 
     * @param m the magnitude
     * @param a the angle
     * @return the new vector
     */
    public static Vec polar(double m, double a) {
        return new Vec(Math.cos(a), Math.sin(a)).mul(m);
    }

    /**
     * Adds the vector v to this vector.
     * 
     * @param v the vector v to add
     * @return this vector after addition
     */
    public Vec add(Vec v) {
        x += v.x;
        y += v.y;
        return this;
    }

    /**
     * Subtracts the vector v from this vector.
     * 
     * @param v the vector v to subtract
     * @return this vector after subtraction
     */
    public Vec sub(Vec v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    /**
     * Multiplies this vector with the scalar s.
     * 
     * @param s the scalar s
     * @return this vector after multiplication
     */
    public Vec mul(double s) {
        x *= s;
        y *= s;
        return this;
    }

    /**
     * Divides this vector by the scalar s.
     * 
     * @param s the scalar s
     * @return this vector after division
     */
    public Vec div(double s) {
        x /= s;
        y /= s;
        return this;
    }

    /**
     * Returns the dot product of this vector and the vector v.
     * 
     * @param v the vector v
     * @return the dot product
     */
    public double dot(Vec v) {
        return x * v.x + y * v.y;
    }

    /**
     * Normalizes this vector to the magnitude m.
     * 
     * @param m the magnitude m
     * @return this vector after normalization
     */
    public Vec normal(double m) {
        return div(mag() / m);
    }

    /**
     * Normalizes this vector to the magnitude 1.
     * 
     * @return this vector after normalization
     */
    public Vec normal() {
        return normal(1);
    }

    /**
     * Returns the squared magnitude of this vector.
     * 
     * @return the squared magnitude
     */
    public double magSq() {
        return dot(this);
    }

    /**
     * Returns the magnitude of this vector.
     * 
     * @return the magnitude
     */
    public double mag() {
        return Math.sqrt(magSq());
    }

    /**
     * Returns the angle of this vector in the range 0 to 2 pi.
     * 
     * @return the angle of this vector
     */
    public double angle() {
        double a = Math.atan2(y, x);
        if (a < 0)
            a += TWO_PI;
        return a;
    }

    /**
     * Rotates this vector by the angle a.
     * 
     * @param a the angle a
     * @return this vector after rotation
     */
    public Vec rotate(double a) {
        double i = x, j = y;
        x = Math.cos(a) * i - Math.sin(a) * j;
        y = Math.sin(a) * i + Math.cos(a) * j;
        return this;
    }

    /**
     * Returns the resulting vector from adding the vectors u and v.
     * 
     * @param u the vector u
     * @param v the vector v
     * @return the resulting vector
     */
    public static Vec add(Vec u, Vec v) {
        return new Vec(u).add(v);
    }

    /**
     * Returns the resulting vector from subtracting the vectors u and v.
     * 
     * @param u the vector u
     * @param v the vector v
     * @return the resulting vector
     */
    public static Vec sub(Vec u, Vec v) {
        return new Vec(u).sub(v);
    }

    /**
     * Returns the resulting vector from multiplying the vector v with the scalar s.
     * 
     * @param v the vector v
     * @param s the scalar s
     * @return the resulting vector
     */
    public static Vec mul(Vec v, double s) {
        return new Vec(v).mul(s);
    }

    /**
     * Returns the resulting vector from dividing the vector v by the scalar s.
     * 
     * @param v the vector v
     * @param s the scalar s
     * @return the resulting vector
     */
    public static Vec div(Vec v, double s) {
        return new Vec(v).div(s);
    }

    /**
     * Returns the resulting vector from normalizing the vector v to the magnitude
     * 1.
     * 
     * @param v the vector v
     * @return the resulting vector
     */
    public static Vec normal(Vec v) {
        return new Vec(v).normal();
    }

    /**
     * Returns the resulting vector from normalizing the vector v to the magnitude
     * m.
     * 
     * @param v the vector v
     * @param m the magnitude m
     * @return the resulting vector
     */
    public static Vec normal(Vec v, double m) {
        return new Vec(v).normal(m);
    }

    /**
     * Returns the distance between the vector u and the vector v.
     * 
     * @param u the vector u
     * @param v the vector v
     * @return the distance
     */
    public static double dist(Vec u, Vec v) {
        return sub(u, v).mag();
    }

    /**
     * Returns the angle between the vectors u and v in the range 0 to pi.
     * 
     * @param u the vector u
     * @param v the vector v
     * @return the angle between
     */
    public static double ang(Vec u, Vec v) {
        return Math.acos(u.dot(v) / u.mag() / v.mag());
    }
}