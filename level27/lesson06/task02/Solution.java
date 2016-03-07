package com.javarush.test.level27.lesson06.task02;

/* Определяем порядок захвата монитора. Сложная.
Реализуйте логику метода isNormalLockOrder, который должен определять:
соответствует ли порядок synchronized блоков в методе someMethodWithSynchronizedBlocks - порядку
передаваемых в него аргументов. Должно выполняться условие:
для разных объектов o1 и o2 isNormalLockOrder(o1, o2) != isNormalLockOrder(o2, o1), для одинаковых объектов одинаковый результат
Метод main не участвует в тестировании.
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        //следующие 4 строки в тестах имеют другую реализацию
        int lock1 = obj1.hashCode();
        int lock2 = obj2.hashCode();
        Object firstLock = lock1 > lock2 ? obj1 : obj2;
        Object secondLock = lock1 > lock2 ? obj2 : obj1;

        synchronized (firstLock) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            synchronized (secondLock) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here
        if (o1.hashCode() > o2.hashCode()) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        String o1s = o1.toString().substring( o1.toString().lastIndexOf("@") ); //test
        String o2s = o2.toString().substring( o2.toString().lastIndexOf("@") ); //test
        System.out.println("o1 = " + o1s + "   o2 = " + o2s + "\n");            //test

        new Thread() {
            @Override
            public void run() {
                try {
                    String o1s = o1.toString().substring( o1.toString().lastIndexOf("@") ); //test
                    String o2s = o2.toString().substring( o2.toString().lastIndexOf("@") ); //test
                    System.out.println("main -> " + this.getName() + " -> isNLO(o1, o2) - " + "("+o1s+", "+o2s+") " +
                            "; o1.hashCode() > o2.hashCode() = " + (o1.hashCode() > o2.hashCode()) +
                            " ["+o1.hashCode()+" > "+o2.hashCode()+"] ; isNLO = " +
                            isNormalLockOrder(solution, o1, o2) //expected boolean b
                    ); //test
                } catch (Exception ignored) {
                }
            }
        }.start();

        Thread.sleep(1000);    //test
        System.out.println();  //test

        new Thread() {
            @Override
            public void run() {
                try {
                    String o1s = o1.toString().substring( o1.toString().lastIndexOf("@") ); //test
                    String o2s = o2.toString().substring( o2.toString().lastIndexOf("@") ); //test
                    System.out.println("main -> " + this.getName() + " -> isNLO(o2, o1) - " + "("+o2s+", "+o1s+") " +
                            "; o2.hashCode() > o1.hashCode() = " + (o2.hashCode() > o1.hashCode()) +
                            " ["+o2.hashCode()+" > "+o1.hashCode()+"] ; isNLO = " + //test
                            isNormalLockOrder(solution, o2, o1) //expected boolean !b
                    ); //test
                } catch (Exception ignored) {
                }
            }
        }.start();

        Thread.sleep(1000);    //test
        System.out.println();  //test

        /* **************************************
                  test (o1, o1) and (o2, o2)
           ************************************** */
        new Thread() {
            @Override
            public void run() {
                try {
                    String o1s = o1.toString().substring( o1.toString().lastIndexOf("@") ); //test
                    System.out.println("main -> " + this.getName() + " -> isNLO(o1, o1) - " + "("+o1s+", "+o1s+") " +
                            "; o1.hashCode() > o1.hashCode() = " + (o1.hashCode() > o1.hashCode()) +
                            " ["+o1.hashCode()+" > "+o1.hashCode()+"] ; isNLO = " + //test
                            isNormalLockOrder(solution, o1, o1)
                    ); //test
                } catch (Exception ignored) {
                }
            }
        }.start();

        Thread.sleep(1000);
        System.out.println();

        new Thread() {
            @Override
            public void run() {
                try {
                    String o2s = o2.toString().substring( o2.toString().lastIndexOf("@") ); //test
                    System.out.println("main -> " + this.getName() + " -> isNLO(o2, o2) - " + "("+o2s+", "+o2s+") " +
                            "; o2.hashCode() > o2.hashCode() = " + (o2.hashCode() > o2.hashCode()) +
                            " ["+o2.hashCode()+" > "+o2.hashCode()+"] ; isNLO = " + //test
                            isNormalLockOrder(solution, o2, o2)
                    ); //test
                } catch (Exception ignored) {
                }
            }
        }.start();
    }
}

