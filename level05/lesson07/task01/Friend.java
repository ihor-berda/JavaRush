package com.javarush.test.level05.lesson07.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя инициализаторами (тремя методами initialize):
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    //Напишите тут ваш код
    public String filename = null;
    public int fileage;
    public String filesex = null;
    public void initialize(String name)
    {
        this.filename = name;
    }
    public void initialize(String name, int age)
    {
        this.filename = name;
        this.fileage = age;
    }
    public void initialize(String name, int age, String sex)
    {
        this.filename = name;
        this.fileage = age;
        this.filesex = sex;
    }

}
