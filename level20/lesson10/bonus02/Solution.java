package com.javarush.test.level20.lesson10.bonus02;

import java.util.ArrayList;
import java.util.List;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 3" + " result: " + String.valueOf( count == 3));



        a = new byte[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1}

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 3" + " result: " + String.valueOf( count == 3));

        a = new byte[][]{
                {1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 4" + " result: " + String.valueOf( count == 4));

        a = new byte[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 1" + " result: " + String.valueOf( count == 1));


        a = new byte[][]{
                {1, 1, 0,},
                {1, 0, 0,},
                {0, 1, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 1" + " result: " + String.valueOf( count == 1));

        a = new byte[][]{
                {1, 0, 0,},
                {0, 0, 0,},
                {0, 0, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 2" + " result: " + String.valueOf( count == 2));

        a = new byte[][]{
                {1, 1, 0,},
                {0, 0, 0,},
                {0, 1, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 2" + " result: " + String.valueOf( count == 2));

        a = new byte[][]{
                {0, 0, 0,},
                {0, 0, 0,},
                {0, 0, 0,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 0" + " result: " + String.valueOf( count == 0));

        a = new byte[][]{
                {1, 0, 1,},
                {0, 0, 1,},
                {1, 0, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 3" + " result: " + String.valueOf( count == 3));

        a = new byte[][]{
                {1, 1, 0,},
                {0, 1, 0,},
                {0, 1, 1,},

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 0" + " result: " + String.valueOf( count == 0));

        a = new byte[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 1}

        };
        count = getRectangleCount(a);
        System.out.println("Count = " + count + ". Должно быть 5" + " result: " + String.valueOf( count == 5));

    }

    public static int getRectangleCount(byte[][] a)
    {
//        int count = 0;
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[i].length; j++) {
//                if (i+1 < a.length && j+1 < a[i].length && a[i][j] == 1 && a[i+1][j] == 0 && a[i][j+1] == 0) {
//                    count++;
//                    a[i][j] = 0;
//                }
////                if (i>0 && j > 0 && a[i][j] == 1 && a[i-1][j] == 0 && a[i][j-1] == 0) {
////                    count++;
////                    continue;
////                }
////                if (j>0 && i+1 < a.length && a[i][j] == 1 && a[i][j-1] == 0 && a[i+1][j] == 0) {
////                    count++;
////                    continue;
////                }
////                if (i>0 && j+1 < a[i].length && a[i][j] == 1 && a[i-1][j] == 0 && a[i][j+1] == 0) {
////                    count++;
////                    continue;
////                }
//                if (j == a[i].length-1 && i == a.length-1 && j > 0 && i > 0 && a[i][j] == 1 && a[i][j-1] == 0 && a[i-1][j] == 0)  { //!!
//                    count++;
//                    a[i][j] = 0;
//                }
//                if (j == a[i].length-1 && i+1 < a.length &&  a[i][j] == 1 && a[i+1][j] == 0) {
//                    count++;
//                    a[i][j] = 0;
//                }
//                if (i == a.length-1 && j > 0 && a[i][j] == 1 && a[i][j-1] == 0) {
//                    count++;
//                    a[i][j] = 0;
//                }
//                if (j == a[i].length-1 && i > 0 && a[i][j] == 1 && a[i-1][j] == 0) {
//                    count++;
//                    a[i][j] = 0;
//                }
//                if (i == a.length-1 && j+1 < a[i].length && a[i][j] == 1 && a[i][j+1] == 0) {
//                    count++;
//                    a[i][j] = 0;
//                }
//            }
//        }
//        return count;
        int hor = a[0].length;
        int ver = a.length;
        int count;
        int verEnd;
        int horEnd;
        int result = 0;
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                if (a[i][j] == 1)
                {
                    verEnd = i;
                    horEnd = j;
                    //по вертикали
                    count = i + 1;
                    while (true)
                    {
                        if (count >= a.length || (a[count][j] == 0 && count < a.length))
                        {
                            verEnd = count - 1;
                            break;
                        }
                        count++;
                    }
                    //по горизонтали
                    count = j + 1;
                    while (true)
                    {
                        if (count >= a[i].length || (a[i][count] == 0 && count < a[i].length))
                        {
                            horEnd = count - 1;
                            break;
                        }
                        count++;
                    }
                    boolean flag = true;
                    for (int p = i; p <= verEnd; p++)
                        for (int q = j; q <= horEnd; q++)
                            if (a[p][q] != 1)
                            {
                                flag = false;
                                break;
                            }
                    if (flag)
                    {
                        for (int p = i; p <= verEnd; p++)
                            for (int q = j; q <= horEnd; q++)
                                a[p][q] = 0;
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
