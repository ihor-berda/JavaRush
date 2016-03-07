package com.javarush.test.level08.lesson08.task01;

import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet()
    {
        //напишите тут ваш код
        Set<String> set = new HashSet<String>();
        set.add("Лала");
        set.add("Лоло");
        set.add("Лулулау");
        set.add("Ллалав");
        set.add("Лалаыл");
        set.add("Лйцулйуц");
        set.add("Лмлмл");
        set.add("Лцулй");
        set.add("Лаи");
        set.add("Лои");
        set.add("Лохи");
        set.add("Ляхи");
        set.add("Лаъи");
        set.add("Лааи");
        set.add("Ллп");
        set.add("Лепс");
        set.add("Ляпс");
        set.add("Люпс");
        set.add("Лва");
        set.add("Лалка");
        return (HashSet<String>)set;


    }
}
