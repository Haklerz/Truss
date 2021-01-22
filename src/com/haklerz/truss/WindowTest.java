package com.haklerz.truss;

public class WindowTest {
    public static void main(String[] args) {
        Window window = new Window("Test", 640, 360, null);
        new Thread(window).start();
    }
}
