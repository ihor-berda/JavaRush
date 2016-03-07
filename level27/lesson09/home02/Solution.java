package com.javarush.test.level27.lesson09.home02;

/* Расставьте wait-notify.
Расставьте wait-notify.
Пример вывода:
Thread-0 MailServer has got: [Person [Thread-1] has written an email 'AAA'] in 1001 ms after start
*/
public class Solution {
    public static void main(String[] args) {
        Mail mail = new Mail();
        Thread server = new Thread(new MailServer(mail));
        Thread amigo = new Thread(new Person(mail));

        server.start();
        amigo.start();
    }
}
