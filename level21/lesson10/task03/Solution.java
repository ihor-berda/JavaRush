package com.javarush.test.level21.lesson10.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Нюансы Exceptions
Классы семейства Utilizator должны утилизировать ресурсы не влияя на работу программы, т.е. программа должна отрабатывать одинаково с любым из Utilizator-ов.
На данный момент это не выполняется из-за неправильных реализаций утилизаторов. Исправьте реализацию утилизаторов.
Метод main не участвует в тестировании.
UtilizatorUtil не менять.
Стек трейс не выводить.
*/
public class Solution {
    private Utilizator utilizator;

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        Solution solution = new Solution();
        solution.setUtilizator(new Utilizator());

        Solution solution2 = new Solution();
        solution2.setUtilizator(new SpecificUtilizator());

        strings.addAll(solution.readFileContent("FakeFileName.txt"));
        strings.addAll(solution2.readFileContent("FakeFileName2.txt"));
        System.out.println("Count of strings is " + strings.size());
    }

    public List<String> readFileContent(String path) {
        List<String> strings = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path filePath = Paths.get(path);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null) {
                strings.add(sCurrentLine);
            }
        } catch (IOException ignored) {

        } finally {
            utilizator.dispose();
        }
        return strings;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("inside finalize - before throwing");
        utilizator.dispose();   //исключения игнорируются в finalize
        System.out.println("inside finalize - after throwing");
    }

    public static class Utilizator {
        protected final UtilizatorUtil util = new UtilizatorUtil();

        public void dispose() {
            //Utilization IS successful
            util.doNothing();
        }
    }

    public static class SpecificUtilizator extends Utilizator {
        @Override
        public void dispose() {
            try
            {
            util.throwException(); }
            catch (Exception e) {}
        }
    }

    public static class UtilizatorUtil {
        public void doNothing() {
        }

        public void throwException() {
            throw new RuntimeException("It`s impossible to dispose resources!");
        }

        public void sout(String message) {
            System.out.println(message);
        }
    }
}
