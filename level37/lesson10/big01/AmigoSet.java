package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by IGOR on 31.01.2016.
 */
public class AmigoSet<E> extends AbstractSet implements Cloneable, Serializable, Set
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends  E> collection) {
        int capacity = 0;
        if (collection.size()/.75f > 16) {
            capacity = (int) (collection.size()/.75f);
        }
        else capacity = 16;
        map = new HashMap<>(capacity);
        this.addAll(collection);
    }

    @Override
    public boolean add(Object o)
    {
        map.put((E) o, PRESENT);
        return map.get(o) != null;
    }

    @Override
    public Iterator<E> iterator()
    {
        Set<E> keys = map.keySet();
        return keys.iterator();
    }

    @Override
    public int size()
    {
        return map.keySet().size();
    }

    @Override
    public Object clone()
    {
        AmigoSet<E> result = null;
        try
        {
            result = (AmigoSet<E>) super.clone();
            result.map = (HashMap<E, Object>) this.map.clone();
        }
        catch (Exception e) {
            throw new InternalError();
        }
        return result;
    }

    @Override
    public boolean isEmpty()
    {
        return map.keySet().isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return map.keySet().contains(o);
    }

    @Override
    public void clear()
    {
        map.keySet().clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return map.keySet().remove(o);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor"));
        out.writeObject(new HashSet<Integer>((Collection<Integer>) map.keySet()));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        addAll((Collection) in.readObject());
    }
}
