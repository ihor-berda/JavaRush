package com.javarush.test.level34.lesson15.big01.view;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by IGOR on 25.01.2016.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;
    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        view.addKeyListener(keyHandler);
        view.setFocusable(true);
    }
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500 ,500);
        if(view.getGameObjects()==null)
            return;
        for (GameObject gameObject: view.getGameObjects().getAll()) {
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT :
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT :
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP :
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN :
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R :
                    eventListener.restart();
                    break;
            }
        }
    }
}
