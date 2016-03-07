package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                for (User u : users)
                {
                    writer.println("yes");
                    String firstName = u.getFirstName();
                    if (firstName == null) firstName = "noFirstName";
                    writer.println(firstName);
                    String lastName = u.getLastName();
                    if (lastName == null) lastName = "noLastName";
                    writer.println(lastName);
                    writer.println(format.format(u.getBirthDate()));
                    writer.println(String.valueOf(u.isMale()));
                    writer.println(u.getCountry());
                }
                writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            while (reader.ready())
            {
                String newUser = reader.readLine();
                if (newUser.equals("yes"))
                {
                    User u = new User();
                    String name = reader.readLine();
                    if (name.equals("noFirstName")) name = null;
                    u.setFirstName(name);
                    String lastName = reader.readLine();
                    if (lastName.equals("noLastName")) lastName = null;
                    u.setLastName(lastName);
                    u.setBirthDate(format.parse(reader.readLine()));
                    if (reader.readLine().equals("true"))
                        u.setMale(true);
                    else u.setMale(false);
                    u.setCountry(User.Country.valueOf(reader.readLine()));
                    users.add(u);
                }
            }
        }
    }
}

