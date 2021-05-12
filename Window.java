package com.myGame.main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersionUID = 4990229990719190523L;

    public Window(int width, int weight, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, weight));
        frame.setMaximumSize(new Dimension(width, weight));
        frame.setMinimumSize(new Dimension(width, weight));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}
