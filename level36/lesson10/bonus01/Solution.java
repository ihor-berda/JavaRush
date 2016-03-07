package com.javarush.test.level36.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("E:\\JavaRushHomeWork\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second\\");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }


    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName);
        String[] classFiles = dir.list();
        File[] files = dir.listFiles();
        for (final File file : files) {
            final String finalPath = dir.getAbsolutePath() + File.separator;
            ClassLoader loader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String className) throws ClassNotFoundException {
                    try
                    {
                        byte[] temp = Files.readAllBytes(Paths.get(finalPath + className + ".class"));
                        return defineClass(null, temp, 0, temp.length);
                    }
                    catch (IOException e) {
                        return super.findClass(className);
                    }
                }
            };
            Class clazz = loader.loadClass(file.getName().substring(0, file.getName().length() - 6));
            if (HiddenClass.class.isAssignableFrom(clazz)) {
                hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key)
    {
        for (Class clazz : hiddenClasses)
        {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
            {
                int count = 0;
                int none = 0;
                try
                {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == 0) {
                            none = count;
                        }
                        count++;
                    }
                    constructors[none].setAccessible(true);
                    return (HiddenClass) constructors[none].newInstance();
                }
                catch (Exception e)
                {
                }
            }
        }
        return null;
    }
}
