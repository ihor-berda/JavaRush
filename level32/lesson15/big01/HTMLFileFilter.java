package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by IGOR on 16.01.2016.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory()) return true;
        else if (f.getName().toUpperCase().toLowerCase().endsWith(".html")) return true;
        else if (f.getName().toUpperCase().toLowerCase().endsWith(".htm")) return true;
        return false;
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}
