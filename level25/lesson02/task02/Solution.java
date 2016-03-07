package com.javarush.test.level25.lesson02.task02;

import java.util.*;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            wheels = new ArrayList<Wheel>();
            for (String wheel : loadWheelNamesFromDB()) {
                if (wheel.equals(Wheel.FRONT_LEFT.toString())) {
                    wheels.add(Wheel.valueOf(wheel));
                }
                else if (wheel.equals(Wheel.FRONT_RIGHT.toString())) {
                    wheels.add(Wheel.valueOf(wheel));
                }
                else if (wheel.equals(Wheel.BACK_LEFT.toString())) {
                    wheels.add(Wheel.valueOf(wheel));
                }
                else if (wheel.equals(Wheel.BACK_RIGHT.toString())){
                    wheels.add(Wheel.valueOf(wheel));
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
