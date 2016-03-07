package com.javarush.test.level38.lesson06.home01;


/**
 * Created by IGOR on 01.02.2016.
 */
public class ExceptionFactory {
    public static Throwable getException (Enum e) {
        if (e != null) {
            String message = e.name().substring(0, 1).toUpperCase() + e.name().substring(1).toLowerCase().replace("_", " ");
            if (e instanceof ExceptionApplicationMessage) return new Exception(message);
            if (e instanceof ExceptionDBMessage) return new RuntimeException(message);
            if (e instanceof ExceptionUserMessage) return new Error(message);
        }
        return new IllegalArgumentException();
    }
}
