package com.haklerz.truss;

public class Input {

    private final boolean[] keyboardKeysDown = new boolean[1 << 16];
    private final boolean[] mouseButtonsDown = new boolean[6];
    private int mouseX;
    private int mouseY;
    private int mouseWheel;

    void setKeyboardKeyDown(int key, boolean state) {
        this.keyboardKeysDown[key] = state;
    }

    void setMouseButtonDown(int button, boolean state) {
        this.mouseButtonsDown[button] = state;
    }

    void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    void addMouseWheel(int rotation) {
        this.mouseWheel += rotation;
    }

    public boolean isKeyboardKeyDown(int key) {
        return keyboardKeysDown[key];
    }

    public boolean isMouseButtonDown(int button) {
        return mouseButtonsDown[button];
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseWheel() {
        return mouseWheel;
    }

}
