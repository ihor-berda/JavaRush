package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.*;

public class Solution {
    private static String tag;
    private static String openTag;
    private static String closeTag;
    private static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine())))
        {
            tag = args[0];
            openTag = "<" + tag;
            closeTag = "</" + tag + ">";
            length = tag.length();
            String input = new String();
            String line = null;
            while ((line = fileReader.readLine()) != null)
            {
                input += line;
            }
            recursiveMethod(input);
        }
    }

    private static void recursiveMethod(String input) {
        int deep = -1;
        int beginTagPosition = 0;
        for (int i = 0; i < input.length() - length - 2 ; i++) {
            if (openTag.equals(input.substring(i, i + length + 1))) {
                if (deep == -1) {
                    deep = 0;
                }
                if (deep == 0) {
                    beginTagPosition = i;
                }
                deep++;
            }
            else if (closeTag.equals(input.substring(i, i + length + 3))) {
                deep--;
            }
            if (deep == 0) {
                int endPositionOfBeginTagPosition = input.indexOf(">", beginTagPosition);
                String content = input.substring(endPositionOfBeginTagPosition + 1, i);
                System.out.println(input.substring(beginTagPosition, i + length + 3));
                recursiveMethod(content);
                deep = -1;
            }
        }
    }
}
