package com.haklerz.truss;


import javax.swing.JFrame;

public class Window implements Runnable {

    private final JFrame frame;
    private final Screen screen;

    public Window(String title, int width, int height, Game game) {
        this.frame = new JFrame(title);
        this.screen = new Screen(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIgnoreRepaint(true);
        frame.add(screen);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    @Override
    public void run() {

        while (true) {
            screen.repaint();
        }
    }

}
