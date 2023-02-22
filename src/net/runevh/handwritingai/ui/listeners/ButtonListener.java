package net.runevh.handwritingai.ui.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonListener implements ActionListener {

    private static final List<Button> buttonEvents = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Button btn : buttonEvents){
            if(btn != null && e.getSource().equals(btn.getObject())){
                btn.getOnClick().run();
                break;
            }
        }
    }

    public static void addButton(Button button){
        buttonEvents.add(button);
    }

    public static class Button {
        private Runnable onClick = null;
        private final Object object;

        public Button(Object obj){
            this.object = obj;
            if(obj instanceof JComponent){
                ((JButton) this.object).addActionListener(new ButtonListener());
            }
            addButton(this);
        }

        public Runnable getOnClick(){
            return onClick;
        }

        public void setOnClick(Runnable onClick){
            this.onClick = onClick;
        }

        public Object getObject(){
            return object;
        }
    }
}

