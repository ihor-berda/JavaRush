package com.javarush.test.level36.lesson04.big01.model.service;

import com.javarush.test.level36.lesson04.big01.Util;
import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();

    @Override
    public User deleteUser(long id) {
        User user = userDao.getUserById(id);
        Util.markDeleted(user);
        userDao.createOrUpdate(user);

        return user;
    }

    @Override
    public User createOrUpdateUser(String name, long id, int level) {
        User user = new User(name, id, level);
        return userDao.createOrUpdate(user);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    public List<User> getAllDeletedUsers() {
        List<User> result = new ArrayList<>();
        for (User user : userDao.getAllUsers()) {
            if (Util.isUserDeleted(user)) {
                result.add(user);
            }
        }

        return result;
    }

    @Override
    public List<User> getUsersBetweenLevels(int fromLevel, int toLevel) {
        //it's better to get all users from DAO by one DB request, but we will use what we have
        List<User> result = new ArrayList<>();
        for (int i = fromLevel; i <= toLevel; i++) {
            result.addAll(userDao.getUsersByLevel(i));
        }

        return result;
    }

    @Override
    public List<User> filterOnlyActiveUsers(List<User> allUsers) {
        //will not change allUsers list, create new one instead of that
        List<User> result = new ArrayList<>();
        for (User user : allUsers) {
            if (User.NULL_USER != user && !Util.isUserDeleted(user)) {
                result.add(user);
            }
        }

        return result;
    }

    @Override
    public User getUsersById(long userId) {
        return userDao.getUsersById(userId);
    }
}
