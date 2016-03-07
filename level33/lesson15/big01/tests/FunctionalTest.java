package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IGOR on 20.01.2016.
 */
public class FunctionalTest
{
    public void testStorage(Shortener shortener) {
        String string1 = "Text";
        String string2 = "Text2";
        String string3 = "Text";
        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);
        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);
        String result1 = shortener.getString(id1);
        String result2 = shortener.getString(id2);
        String result3 = shortener.getString(id3);
        Assert.assertEquals(result1, string1);
        Assert.assertEquals(result2, string2);
        Assert.assertEquals(result3, string3);
    }
    @Test
    public void testHashMapStorageStrategy() { testStorage(new Shortener(new HashMapStorageStrategy())); }
    @Test
    public void testOurHashMapStorageStrategy() { testStorage(new Shortener(new OurHashMapStorageStrategy()));}
    @Test
    public void testFileStorageStrategy() { testStorage(new Shortener(new FileStorageStrategy()));}
    @Test
    public void testHashBiMapStorageStrategy() { testStorage(new Shortener(new HashBiMapStorageStrategy()));}
    @Test
    public void testDualHashBidiMapStorageStrategy() { testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));}
    @Test
    public void testOurHashBiMapStorageStrategy() { testStorage(new Shortener(new OurHashBiMapStorageStrategy()));}
}
