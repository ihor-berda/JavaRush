package com.javarush.test.level25.lesson07.task01;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/* Работать в поте лица!
Реализуйте логику метода interrupt, который должен прерывать трэд предварительно закрыв используемые ресурсы
Используйте метод super-класса в блоке finally
*/
public class Solution extends Thread {
    private static final int BUFFER_SIZE = 2000;    //2000 bytes
    private final Socket socket;
    private final InputStream in;

    public Solution(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        //implement logic here
        try {
            socket.close();
            in.close();
        }
        catch (IOException e) {}
        Thread.interrupted();
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else {
                    if (count > 0) {
                        //process buffer here
                        interrupt();
                    }
                }
            }
        } catch (IOException ignored) {}
        finally
        {
            super.interrupt();
        }
    }
}
