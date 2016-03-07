package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IGOR on 28.01.2016.
 */
public class MainModel implements Model {
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        modelData.setUsers(getActiveUsers(userService.getUsersBetweenLevels(1, 100)));
        modelData.setDisplayDeletedUserList(false);
    }

    public void loadDeletedUsers() {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);
    }

    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    public void deleteUserById(long id) {
        User user = userService.deleteUser(id);
        modelData.setUsers(getActiveUsers(userService.getUsersBetweenLevels(1, 100)));
        modelData.setDisplayDeletedUserList(false);
    }

    private List<User> getActiveUsers(List<User> userList) {
        return userService.filterOnlyActiveUsers(userList);
    }

    public void changeUserData(String name, long id, int level) {
        User user = userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(getActiveUsers(userService.getUsersBetweenLevels(1, 100)));
        modelData.setDisplayDeletedUserList(false);
    }
}
