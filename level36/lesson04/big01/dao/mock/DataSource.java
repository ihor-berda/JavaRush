package com.javarush.test.level36.lesson04.big01.dao.mock;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataSource {
    private static DataSource ourInstance = new DataSource();

    public static DataSource getInstance() {
        return ourInstance;
    }

    private DataSource() {
    }

    private List<User> users = new LinkedList<User>() {{
        add(new User("Ivanov", 123l, 1));
        add(new User("Petrov", 124l, 2));
        add(new User("Petrov", 125l, 1));
        add(new User("Sidorov", 126l, 2));
    }};

    private long maxUserId = 126l;

    public List<User> getUsers() {
        return users;
    }

    public User createOrUpdate(User newUser) {
        if (newUser == User.NULL_USER)
            return User.NULL_USER;

        //new User
        if (newUser.getId() == 0)
            return createNewUser(newUser);
        else
            return updateUser(newUser);
    }

    private User createNewUser(User newUser) {
        User clone = newUser.clone(++maxUserId);
        users.add(clone);
        return clone;
    }

    private User updateUser(User newUser) {
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                user.setName(newUser.getName());
                user.setLevel(newUser.getLevel());
                return user;
            }
        }
        //if we didn't find such a user
        return User.NULL_USER;
    }
}
