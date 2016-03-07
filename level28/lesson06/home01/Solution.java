package com.javarush.test.level28.lesson06.home01;

/* Приоритеты в Threads
В отдельном файле создайте класс MyThread унаследовавшись от Thread. MyThread должен:
1. иметь возможность быть созданным используя любой конструктор супер-класса  (Alt+Insert)
2. приоритет у трэдов должен проставляться циклично от минимального значения до максимального значения.
3. если у трэда установлена трэд-группа(ThreadGroup), то приоритет трэда не может быть больше
максимального приоритета его трэд-группы
*/
public class Solution {

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            System.out.print(new MyThread().getPriority() + " ");
        }
        //output
        //1 2 3 4 5 6 7 8 9 10 1 2

        System.out.println();
        ThreadGroup group = new ThreadGroup("someName");
        group.setMaxPriority(7);
        for (int i = 0; i < 12; i++) {
            System.out.print(new MyThread(group, "name" + i).getPriority() + " ");
        }
        //output
        //3 4 5 6 7 7 7 7 1 2 3 4
    }

}
