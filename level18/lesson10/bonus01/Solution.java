package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;
import java.util.Arrays;

public class Solution {
    private static byte key = 10;

    public static void main(String[] args) throws IOException {
        if(args.length < 3) return;

        FileInputStream in = new FileInputStream(args[1]);
        FileOutputStream out = new FileOutputStream(args[2]);

        while (in.available() > 0){
            byte[] data = new byte[in.available()];
            in.read(data);
            System.out.println(Arrays.toString(crypt(data)));
            out.write(crypt(data));
        }

        in.close();
        out.close();

    }

    private static byte[] crypt(byte[] data){
        byte[] res = new byte[data.length];

        for(int i = 0; i < data.length; i++){
            res[i] = (byte)(data[i] ^ key);
        }

        return res;
    }

}
