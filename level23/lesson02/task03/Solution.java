package com.javarush.test.level23.lesson02.task03;

/* Запретите создание экземпляров класса
Запретите создание экземпляров класса Listener.
*/
public class Solution {

    public abstract static class Listener {
        public void onMouseDown(int x, int y) {
            //do something on mouse down event
        }

        public void onMouseUp(int x, int y) {
            //do something on mouse up event
        }
    }
}
