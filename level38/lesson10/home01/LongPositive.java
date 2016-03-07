package com.javarush.test.level38.lesson10.home01;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LongPositive {
}
