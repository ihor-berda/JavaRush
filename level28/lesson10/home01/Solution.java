package com.javarush.test.level28.lesson10.home01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/* Plants vs Zombies
1. Почитайте про java.util.concurrent.Phaser
2. Расставь методы в нужных местах
-arriveAndDeregister() - начинает запускать методы run у тасок
-arriveAndAwaitAdvance() - ждет, когда все трэды будут созданы

Пример вывода см. в output.txt
Логика:
Сначала все персонажи присоединяются к игре.
После того, как все персонажи присоединятся к игре и готовы играть, выводится фраза "Игра началась!".
После этого персонажи вступают в игру, после чего умирают.
Не должно быть закоментированного кода
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        List<Character> characters = new ArrayList<>();
        characters.add(new Plant());
        characters.add(new Plant());
        characters.add(new Zombie());
        characters.add(new Zombie());
        characters.add(new Zombie());
        start(characters);
    }

    private static boolean isEveryoneReady = false;

    private static void start(List<Character> characters) throws InterruptedException {
        final Phaser phaser = new Phaser(characters.size());

        for (final Character character : characters) {
            final String member = character.toString();
            System.out.println(member + " присоединился к игре");
            new Thread() {
                @Override
                public void run() {
                    System.out.println(member + " готовится играть");
                    phaser.arriveAndAwaitAdvance();
                    if (!isEveryoneReady) {
                        isEveryoneReady = true;
                        System.out.println("Игра началась!");
                    }
                    character.run();
                }
            }.start();
        }
        phaser.arriveAndDeregister();
    }
}
