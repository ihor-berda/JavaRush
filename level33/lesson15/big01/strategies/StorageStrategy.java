package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by IGOR on 19.01.2016.
 */
public interface StorageStrategy
{
    boolean containsKey(Long key);
    boolean containsValue(String value);
    void put(Long key, String value);
    Long getKey(String value);
    String getValue(Long key);
}
