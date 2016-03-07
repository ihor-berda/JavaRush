package com.javarush.test.level17.lesson10.home08;

/* Банкомат
Разберись, как работает программа
Во время тестирования лог содержит следующее:
.....
Добавляем 100, на счету 1100
Добавляем 100, на счету 1200
Тратим 1000, на счету 100
Недостаточно денег
.....

Создан баг: При списании денег со счета теряются деньги
Найти и исправить ошибку
*/

public class Bankomat {

    static BankAccount account = new BankAccount("Amigo");

    public static volatile boolean isStopped;

    public static void main(String[] args) throws InterruptedException {
        addMoney.start();
        new SpendThread();
        new SpendThread();
        new SpendThread();
        Thread.sleep(4000);
        isStopped = true;
    }

    private static Thread addMoney = new Thread() {
        @Override
        public void run() {
            while (!isStopped) {
                account.deposit("1000");            //кладем на счет
                account.deposit("100");
                account.deposit("100");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    };


    public static class SpendThread extends Thread {
        public SpendThread() {
            start();
        }

        @Override
        public void run() {
            while (!isStopped) {
                synchronized (account){
                    try {
                        account.withdraw("1000");             //снимаем со счета
                    } catch (NotEnoughMoneyException e) {
                        System.out.println("Недостаточно денег");
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    ;
}