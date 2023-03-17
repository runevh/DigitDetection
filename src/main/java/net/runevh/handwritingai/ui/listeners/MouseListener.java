package net.runevh.handwritingai.ui.listeners;

import net.runevh.handwritingai.ui.DrawingBoard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {


    @Override
    public void mouseDragged(MouseEvent e){
        DrawingBoard.getInstance().addPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        DrawingBoard.getInstance().mouseUp();
    }
}
