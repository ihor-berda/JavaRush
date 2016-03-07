package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by IGOR on 19.01.2016.
 */
public class FileBucket
{
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            path.toFile().deleteOnExit();
        }
        catch (IOException e) {}
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        }
        catch (IOException e){
        }
        return 0L;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
        }
        catch (IOException ignored) {}
    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));){
            Entry result = (Entry) ois.readObject();
            return result;
        }
        catch (IOException | ClassNotFoundException e) {}
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        }
        catch (IOException e) {}
    }
}
