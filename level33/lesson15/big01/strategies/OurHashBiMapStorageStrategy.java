package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;

/**
 * Created by IGOR on 20.01.2016.
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy
{
    private HashMap<Long, String> k2v = new HashMap<>();
    private HashMap<String, Long> v2k = new HashMap<>();
    @Override
    public boolean containsKey(Long key)
    {
        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(String value)
    {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value)
    {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    @Override
    public Long getKey(String value)
    {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key)
    {
        return k2v.get(key);
    }
}
