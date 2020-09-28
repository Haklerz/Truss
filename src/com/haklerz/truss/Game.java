package com.haklerz.truss;

/**
 * Interface which all Truss games must implement.
 */
public interface Game {

    public abstract void init(Setup setup);

    public abstract void loop(Time time, Draw draw);
}