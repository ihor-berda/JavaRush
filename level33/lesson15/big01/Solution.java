package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IGOR on 19.01.2016.
 */
public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String s : strings) { ids.add(shortener.getId(s)); }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys) { strings.add(shortener.getString(key)); }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Long time1 = new Date().getTime();
        Set<Long> keys = getIds(shortener, strings);
        Long time2 = new Date().getTime();
        Long duration = time2 - time1;
        Helper.printMessage(duration.toString());
        Long time3 = new Date().getTime();
        Set<String> stringsSet = getStrings(shortener, keys);
        Long time4 = new Date().getTime();
        Long duration2 = time4 - time3;
        Helper.printMessage(duration2.toString());
        if (stringsSet.equals(strings)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args)
    {
        Solution.testStrategy(new HashMapStorageStrategy(), 10000L);
        Solution.testStrategy(new OurHashMapStorageStrategy(), 10000L);
        Solution.testStrategy(new FileStorageStrategy(), 10L);
        Solution.testStrategy(new OurHashBiMapStorageStrategy(), 10000L);
        Solution.testStrategy(new HashBiMapStorageStrategy(), 10000L);
        Solution.testStrategy(new DualHashBidiMapStorageStrategy(), 10000L);
    }
}
