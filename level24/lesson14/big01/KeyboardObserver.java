package com.javarush.test.level24.lesson14.big01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ArrayBlockingQueue;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Queue;

public class KeyboardObserver extends Thread
{
    private Queue<KeyEvent> keyEvents = new ArrayBlockingQueue<KeyEvent>(100);

    private JFrame frame;

    @Override
    public void run()
    {
        frame = new JFrame("KeyPress Tester");
        frame.setTitle("Transparent JFrame Demo"); // устанавливаем заголовок
        // это нужно для того, чтобы при закрытии окна закрывалась и программа, иначе она останется висеть в процесах
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setUndecorated(true); // не включено никаких украшений
        frame.setSize(400, 400); // указываем размер окна
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // указываем состояние кадра
        frame.setLayout(new GridBagLayout());

        frame.setOpacity(0.0f);
        frame.setVisible(true); // приложение запускается

        frame.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                //do nothing
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                System.exit(0);
            }
        });


        frame.addKeyListener(new KeyListener()
        {

            public void keyTyped(KeyEvent e)
            {
                //do nothing
            }

            public void keyReleased(KeyEvent e)
            {
                //do nothing
            }

            public void keyPressed(KeyEvent e)
            {
                keyEvents.add(e);
            }
        });
    }


    public boolean hasKeyEvents()
    {
        return !keyEvents.isEmpty();
    }

    public KeyEvent getEventFromTop()
    {
        return keyEvents.poll();
    }
}

