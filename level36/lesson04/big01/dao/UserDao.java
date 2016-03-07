package com.javarush.test.level36.lesson04.big01.dao;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.dao.mock.DataSource;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DataSource dataSource = DataSource.getInstance();

    public User getUserById(long id) {
        List<User> users = dataSource.getUsers();
        for (User user : users) {
            if (user.getId() == id) {
                return user.clone();
            }
        }
        return User.NULL_USER;
    }

    public List<User> getUsersByName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException();

        List<User> users = dataSource.getUsers();
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (name.equals(user.getName())) {
                addUserToResult(result, user);
            }
        }
        return result;
    }

    public List<User> getAllUsers() {
        List<User> users = dataSource.getUsers();
        List<User> result = new ArrayList<>();
        for (User user : users) {
            addUserToResult(result, user);
        }
        return result;
    }

    public List<User> getUsersByLevel(int level) {
        if (level < 1) throw new IllegalArgumentException();

        List<User> users = dataSource.getUsers();
        List<User> result = new ArrayList<>();

        for (User user : users) {
            if (level == user.getLevel()) {
                addUserToResult(result, user);
            }
        }
        return result;
    }

    public void addUserToResult(List<User> result, User user) {
        User clone = user.clone();

        //skip bad users
        if (clone != User.NULL_USER) {
            result.add(clone);
        }
    }

    public User createOrUpdate(User user) {
        return dataSource.createOrUpdate(user);
    }

    public User getUsersById(long userId) {
        if (userId < 1) throw new IllegalArgumentException();

        List<User> users = dataSource.getUsers();
        for (User user : users) {
            if (userId == user.getId()) {
                return user;
            }
        }
        return User.NULL_USER;
    }
}
