package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.female.FemaleFactory;
import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by IGOR on 31.01.2016.
 */
public class FactoryProducer
{
    public static enum HumanFactoryType {
        MALE,
        FEMALE;
    }

    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType) {
        switch (humanFactoryType) {
            case MALE: return new MaleFactory();
            case FEMALE: return new FemaleFactory();
            default: return null;
        }
    }
}
