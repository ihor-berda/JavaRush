package com.javarush.test.level38.lesson10.home02;

public @interface Author {
    //напиши свой код
    String value() default "";
    Position position() default Position.OTHER;
}
