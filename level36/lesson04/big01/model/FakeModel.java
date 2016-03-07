package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IGOR on 28.01.2016.
 */
public class FakeModel implements Model
{
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        List<User> userList = new ArrayList<>();
        userList.add(new User("A", 1, 1));
        userList.add(new User("B", 2, 1));
        modelData.setUsers(userList);
    }

    @Override
    public void loadDeletedUsers()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level)
    {
        throw new UnsupportedOperationException();
    }
}
