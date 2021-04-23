package com.haklerz.truss;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

public class DefaultGraphicsConfiguration {
    private static GraphicsConfiguration configuration = null;

    private DefaultGraphicsConfiguration() {
    }

    public static GraphicsConfiguration getInstance() {
        if (configuration == null) {
            configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                    .getDefaultConfiguration();
        }
        return configuration;
    }
}
