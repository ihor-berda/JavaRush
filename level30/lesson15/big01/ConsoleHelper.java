package com.javarush.test.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IGOR on 04.01.2016.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String text = null;
        try {
            text = reader.readLine();
            if (text == null || text.equals("")) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                text = readString();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static int readInt() {
        int number = 0;
        try {
            try {
                number = Integer.parseInt(reader.readLine());
            }
            catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                number = readInt();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }
}
