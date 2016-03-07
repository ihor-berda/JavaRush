package com.javarush.test.level23.lesson04.task02;

/* Inner 2
В классе SuperUser метод getDescription должен учитывать страну и город, т.е. возвращать результат аналогичный следующему:
My name is George. I'm from the USA, Seattle.
Используйте возможности иннер класса.
*/
public class Solution {
    private String country;
    private String city;

    public Solution(String country, String city) {
        this.country = country;
        this.city = city;
    }

    /*т.к. модификатор иннер класса private, то чтобы вызвать метод getDescription из другого внешнего класса,
    нужно обернуть его вызов в какой-то public метод*/
    public String getDescriptionOfUser(String name) {
        return getTrickyUser(name).getDescription();
    }

    public SuperUser getTrickyUser(String name) {
        return new SuperUser(name);
    }

    private class SuperUser {
        private String name;

        //доступ к этому методу возможен только внутри класса Solution, т.к. модификатор иннер класса private
        public SuperUser(String name) {
            this.name = name;
        }

        //доступ к этому методу возможен только внутри класса Solution, т.к. модификатор иннер класса private
        public String getDescription() {
            return String.format("My name is %s. I'm from %s, %s.", this.name, Solution.this.country, Solution.this.city);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution("the USA", "Seattle");
        //внутри класса Solution (а сейчас мы внутри) к методу getDescription можно обращаться обоими способами
        System.out.println(solution.getTrickyUser("George").getDescription());
        //а из любого другого внешнего класса только так:
        System.out.println(solution.getDescriptionOfUser("George"));
    }
}
