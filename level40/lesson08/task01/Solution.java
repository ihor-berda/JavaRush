package com.javarush.test.level40.lesson08.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/* Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http.
Классы HttpURLConnection, HttpClient и т.д. не использовать.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            String host = url.toString().substring(url.toString().indexOf("/") + 2);
            host = host.substring(0, host.lastIndexOf("/"));
            String requestString = url.toString().substring(url.toString().lastIndexOf(host) + host.length());
            Socket socket = new Socket(host, 80);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("GET " + requestString + " HTTP/1.1");
            writer.println("Host: " + host);
            writer.println("");
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}