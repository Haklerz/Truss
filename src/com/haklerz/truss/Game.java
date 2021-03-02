package com.haklerz.truss;

/**
 * Interface which all Truss games must implement.
 */
public interface Game {

    public abstract void loop(Draw draw, Input input);
}