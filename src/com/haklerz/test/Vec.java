package com.haklerz.test;

/**
 * Simple vector class
 */
public class Vec {
    public double x, y;

    /**
     * Creates a vector with x and y components.
     * @param x The x component
     * @param y The y component
     */
    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a vector with equal x and y components.
     * @param s The x and y component
     */
    public Vec(double s) {
        this(s, s);
    }

    /**
     * Creates a zero vector with x and y components equal to 0.
     */
    public Vec() {
        this(0);
    }

    /**
     * Creates a copy of a given vector.
     * @param u The vector to copy
     */
    public Vec(Vec u) {
        this(u.x, u.y);
    }

    // Non-static methods

    /**
     * Adds a vector u to the vector.
     * @param u The vector to be added
     * @return The vector after addition
     */
    public Vec add(Vec u) {
        x += u.x;
        y += u.y;
        return this;
    }

    /**
     * Subtracts a vector u from the vector.
     * @param u The vector to be subtracted
     * @return The vector after subtraction
     */
    public Vec sub(Vec u) {
        x -= u.x;
        y -= u.y;
        return this;
    }

    /**
     * Multiplies the vector by a given scalar s.
     * @param s The scalar
     * @return The vector after multiplication
     */
    public Vec mul(double s) {
        x *= s;
        y *= s;
        return this;
    }

    /**
     * Divides the vector by a given scalar s.
     * @param s The scalar
     * @return The vector after divition
     */
    public Vec div(double s) {
        x /= s;
        y /= s;
        return this;
    }

    /**
     * Returns the dot product of a vector u and the vector.
     * @param u The given vector
     * @return The dot product
     */
    public double dot(Vec u) {
        return x * u.x + y * u.y;
    }

    /**
     * Returns the squared magnitude of the vector.
     * @return The squared magnitude of the vector
     */
    public double magSq() {
        return dot(this);
    }

    /**
     * Returns the magnitude of the vector.
     * @return The magnitude of the vector
     */
    public double mag() {
        return Math.sqrt(magSq());
    }

    /**
     * Returns the angle of the vector in the range [-pi, pi].
     * @return The angle of the vector
     */
    public double ang() {
        return Math.atan2(y, x);
    }
    
    /**
     * Returns the angle between the vector and a vector u in the range [0, pi].
     * @param u The given vector
     * @return The angle between the vectors
     */
    public double ang(Vec u) {
        return Math.acos(dot(u) / (mag() * u.mag()));
    }

    /**
     * Normalizes the vector to a magnitude of 1.
     * @return The vector after normalization
     */
    public Vec normalize() {
        return div(mag());
    }

    // Static methods

    /**
     * Adds two vectors u and v.
     * @param u The vector u
     * @param v The vector v
     * @return The resulting vector
     */
    public static Vec add(Vec u, Vec v) {
        return new Vec(u).add(v);
    }

    /**
     * Subtracts vector v from u and returns the resulting vector.
     * @param u The vector u
     * @param v The vector v
     * @return The resulting vector
     */
    public static Vec sub(Vec u, Vec v) {
        return new Vec(u).sub(v);
    }

    public static Vec mul(Vec u, double s) {
        return new Vec(u).mul(s);
    }

    public static Vec div(Vec u, double s) {
        return new Vec(u).div(s);
    }

    public static Vec normalize(Vec u) {
        return new Vec(u).normalize();
    }

    /**
     * Returns the distance between two vectors.
     * @param u
     * @param v
     * @return
     */
    public static double dist(Vec u, Vec v) {
        return sub(u, v).mag();
    }

    public static Vec lerp(Vec u, Vec v, double t) {
        return sub(v, u).mul(t).add(u);
    }

    public Vec perp(Vec v) {
        return new Vec(-v.y, v.x);
    }
    
    public static Vec polar(double mag, double ang) {
        return new Vec(Math.cos(ang), Math.sin(ang)).mul(mag);
    }
}