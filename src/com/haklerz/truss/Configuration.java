package com.haklerz.truss;

public class Configuration {
    private int width, height;
    private String title;

    /**
     * 
     * @param width  the width to set
     * @param height the height to set
     */
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
}
