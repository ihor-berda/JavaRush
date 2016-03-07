package com.javarush.test.level30.lesson15.big01.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGuiView {
    private final ClientGuiController controller;

    private JFrame frame = new JFrame("Чат"); // пустое окно с заголовком "Чат"
    private JTextField textField = new JTextField(50); // пустое поле "textField" в которое может поместиться 50 символов
    private JTextArea messages = new JTextArea(10, 50); // пустое поле "messages" высотой в 10 символов, шириной - 50
    private JTextArea users = new JTextArea(10, 10); // тоже самое, поле "users"

    public ClientGuiView(ClientGuiController controller) {
        this.controller = controller;
        initView();
    }

    private void initView() {
        textField.setEditable(false); // поле не редактируемо
        messages.setEditable(false); // тоже самое
        users.setEditable(false); // тоже самое

        frame.getContentPane().add(textField, BorderLayout.NORTH); // добавляем поле textField сверху
        frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST); // добавляет полосу прокрутки на messages слева
        frame.getContentPane().add(new JScrollPane(users), BorderLayout.EAST); // тоже самое на users, справа
        frame.pack(); // размер окна frame - оптимальный
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // при закрытии окна программа завершается
        frame.setVisible(true); // окно видно на экране

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // добавляем действие
                controller.sendTextMessage(textField.getText());
                textField.setText("");
            }
        });
    }

    public String getServerAddress() {
        return JOptionPane.showInputDialog( // новое диаголовое окно
                frame,
                "Введите адрес сервера:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE); // это вопрос
    }

    public int getServerPort() {
        while (true) {
            String port = JOptionPane.showInputDialog(
                    frame,
                    "Введите порт сервера:",
                    "Конфигурация клиента",
                    JOptionPane.QUESTION_MESSAGE); // вопрос
            try {
                return Integer.parseInt(port.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Был введен некорректный порт сервера. Попробуйте еще раз.",
                        "Конфигурация клиента",
                        JOptionPane.ERROR_MESSAGE); // ошибка
            }
        }
    }

    public String getUserName() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите ваше имя:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE); // вопрос
    }

    public void notifyConnectionStatusChanged(boolean clientConnected) {
        textField.setEditable(clientConnected);
        if (clientConnected) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Соединение с сервером установлено",
                    "Чат",
                    JOptionPane.INFORMATION_MESSAGE); // информация
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    "Клиент не подключен к серверу",
                    "Чат",
                    JOptionPane.ERROR_MESSAGE); // ошибка
        }

    }

    public void refreshMessages() {
        messages.append(controller.getModel().getNewMessage() + "\n");
    }

    public void refreshUsers() {
        ClientGuiModel model = controller.getModel();
        StringBuilder sb = new StringBuilder();
        for (String userName : model.getAllUserNames()) {
            sb.append(userName).append("\n");
        }
        users.setText(sb.toString());
    }
}
