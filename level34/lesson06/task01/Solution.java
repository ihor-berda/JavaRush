package com.javarush.test.level34.lesson06.task01;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* Слабые ссылки
Разберитесь в примере.
Внутри метода main в нужном месте создайте слабую ссылку reference на объект monkey.
*/
public class Solution {
    public static Helper helper = new Helper();

    public static class Monkey {
        private String name;

        public Monkey(String name) {
            this.name = name;
        }

        protected void finalize() {
            Helper.isFinalized = true;
            System.out.format("Bye-Bye, %s!\n", name);
        }
    }

   public static void main(String args[]) throws InterruptedException {
        helper.startTime();

        Monkey monkey = new Monkey("Simka");

       WeakReference<Monkey> reference = new WeakReference<Monkey>(monkey);

        helper.callGC();

        monkey = null;

        helper.callGC();
        helper.heapConsuming();

        if (reference.get() == null)
            System.out.println("Finalized");

        helper.finish();
    }

    public static class Helper {
        public static boolean isFinalized;

        private long startTime;

        void startTime() {
            this.startTime = System.currentTimeMillis();
        }

        int getTime() {
            return (int) (System.currentTimeMillis() - startTime) / 1000;
        }

        void callGC() throws InterruptedException {
            System.gc();
            Thread.sleep(1000);
        }

        void heapConsuming() {
            try {
                List<Solution> heap = new ArrayList<Solution>(100000);
                while (!isFinalized) {
                    heap.add(new Solution());
                }
            } catch (OutOfMemoryError e) {
                System.out.println("Out of memory error raised");
            }
        }

        public void finish() {
            System.out.println("Done");
            System.out.println("It took " + getTime() + " sec");
        }
    }
}
