package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        ArrayList<Human> list = new ArrayList<Human>();
        ArrayList<Human> list2 = new ArrayList<Human>();
        ArrayList<Human> list3 = new ArrayList<Human>();

        Human son = new Human("Vasya", true, 13, new ArrayList<Human>());
        Human son2 = new Human("Petya", true, 6, new ArrayList<Human>());
        Human daugther = new Human("Masha", false, 18, new ArrayList<Human>());
        list.add(son);
        list.add(son2);
        list.add(daugther);
        Human father = new Human("Papka", true, 39, list);
        Human mother = new Human("Mamka", false, 37, list);
        list2.add(father);
        Human grandpafather = new Human("Papka papki", true, 56, list2);
        Human grandmafather = new Human("Mamka papki", false, 55, list2);
        list3.add(mother);
        Human grandpamother = new Human("Papka mamki", true, 57, list3);
        Human grandmamother = new Human("Mamka mamki", false, 57, list3);
        System.out.println(son);
        System.out.println(son2);
        System.out.println(daugther);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(grandpafather);
        System.out.println(grandmafather);
        System.out.println(grandpamother);
        System.out.println(grandmamother);
    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }


        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
