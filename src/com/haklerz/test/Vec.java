package com.haklerz.test;

public class Vec {
    public double x, y;

    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec(double s) {
        this(s, s);
    }

    public Vec() {
        this(0);
    }

    public Vec(Vec u) {
        this(u.x, u.y);
    }

    // Non-static methods

    public Vec add(Vec u) {
        x += u.x;
        y += u.y;
        return this;
    }

    public Vec sub(Vec u) {
        x -= u.x;
        y -= u.y;
        return this;
    }

    public Vec mul(double s) {
        x *= s;
        y *= s;
        return this;
    }

    public Vec div(double s) {
        x /= s;
        y /= s;
        return this;
    }

    public double dot(Vec u) {
        return x * u.x + y * u.y;
    }

    public double magSq() {
        return dot(this);
    }

    public double mag() {
        return Math.sqrt(magSq());
    }

    public double ang() {
        return Math.atan2(y, x);
    }
    
    public double ang(Vec u) {
        return Math.acos(dot(u) / (mag() * u.mag()));
    }

    public Vec normalize() {
        return div(mag());
    }

    // Static methods

    public static Vec add(Vec u, Vec v) {
        return new Vec(u).add(v);
    }

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