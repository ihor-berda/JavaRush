package com.javarush.test.level33.lesson15.big01.strategies;


/**
 * Created by IGOR on 19.01.2016.
 */
public class FileStorageStrategy implements StorageStrategy
{
    private long bucketSizeLimit = 10000;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;

    public long getBucketSizeLimit()
    {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit)
    {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    private int hash(Long k)
    {
        return k.hashCode();
    }
    private int indexFor(int hash, int length){
        return hash & (length-1);
    }
    private Entry getEntry(Long key){
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) return entry;
            }
            entry = entry.next;
        }
        return null;
    }
    private void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }
    private void transfer(FileBucket[] newTable){
        int newCapacity = newTable.length;
        for (int i = 0; i < table.length; i++) {
            Entry e = table[i].getEntry();
            while (e != null) {
                Entry next = e.next;
                int index = indexFor(e.hash, newCapacity);
                if (newTable[index] == null) {
                    e.next = null;
                    newTable[index] = new FileBucket();
                }
                else {
                    e.next = newTable[i].getEntry();
                }
                newTable[index].putEntry(e);
                e = next;
            }
            table[i].remove();
        }
    }
    private void addEntry(int hash, Long key, String value, int bucketIndex){
        FileBucket fileBucket = table[bucketIndex];
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
        }
    }
    private void createEntry(int hash, Long key, String value, int bucketIndex){
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }
    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }
    @Override
    public boolean containsValue(String value)
    {
        for (int i = 0; i < table.length ; i++) {
            if (table[i] == null) continue;
            Entry e = table[i].getEntry();
            while (e != null) {
                if (e.value.equals(value)) return true;
                e = e.next;
            }
        }
        return false;
    }
    @Override
    public void put(Long key, String value)
    {
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        if (table[i] != null) {
            Entry e = table[i].getEntry();
            while (e != null) {
                if (e.getKey().equals(key)) {
                    e.value = value;
                    return;
                }
                e = e.next;
            }
            addEntry(hash, key, value, i);
        }
        else {
            createEntry(hash, key, value, i);
        }
    }
    @Override
    public Long getKey(String value)
    {
        for (int i = 0; i < table.length; i++)
        {
            if (table[i] == null) continue;
            Entry e = table[i].getEntry();
            while (e != null) {
                if (e.value.equals(value)) return e.key;
                e = e.next;
            }
        }
        return null;
    }
    @Override
    public String getValue(Long key)
    {
        return null == getEntry(key) ? null : getEntry(key).value;
    }
}
