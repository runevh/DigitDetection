package net.runevh.handwritingai.ui;

import net.runevh.handwritingai.ui.listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JFrame {
    private static Container container;
    private static DrawingBoard board = new DrawingBoard();

    public Canvas(){
        container = getContentPane();

        ButtonListener.Button clear = new ButtonListener.Button(new JButton("Clear"));
        clear.setOnClick(() -> DrawingBoard.getInstance().clearScreen());
        board.add((JButton) clear.getObject());
        container.add(board);
    }

    public static void init(){
        Canvas frame = new Canvas();
        frame.setVisible(true);
        frame.setSize(600, 300);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
