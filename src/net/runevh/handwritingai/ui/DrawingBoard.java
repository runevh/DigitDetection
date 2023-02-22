package net.runevh.handwritingai.ui;

import net.runevh.handwritingai.ui.listeners.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingBoard extends JPanel {

    private final List<List<Point>> points = new ArrayList<>(new ArrayList<>());
    int currentIt = -1;

    private static DrawingBoard instance;

    public void mouseUp(){
        currentIt++;
        points.add(currentIt, new ArrayList<>());
    }

    public void addPoint(int x, int y){
        points.get(currentIt).add(new Point(x, y));
        repaint();
    }

    public void clearScreen(){
        points.clear();
        currentIt = -1;
        mouseUp();
        repaint();
    }

    public DrawingBoard(){
        mouseUp();
        instance = this;
        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());
    }

    public static DrawingBoard getInstance(){
        return instance;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(6));
        g.setColor(Color.BLACK);

        for (List<Point> point : points) {
            int[] x = new int[point.size()], y = new int[point.size()];
            for (int j = 0; j < point.size(); j++) {
                x[j] = point.get(j).x;
                y[j] = point.get(j).y;
            }
            g.drawPolyline(x, y, point.size());
        }


    }


}
