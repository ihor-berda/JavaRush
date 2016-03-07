package com.javarush.test.level36.lesson04.big01;

import com.javarush.test.level36.lesson04.big01.controller.Controller;
import com.javarush.test.level36.lesson04.big01.model.FakeModel;
//import com.javarush.test.level36.lesson04.big01.model.MainModel;
import com.javarush.test.level36.lesson04.big01.model.MainModel;
import com.javarush.test.level36.lesson04.big01.model.Model;
//import com.javarush.test.level36.lesson04.big01.view.EditUserView;
import com.javarush.test.level36.lesson04.big01.view.EditUserView;
import com.javarush.test.level36.lesson04.big01.view.UsersView;

public class Solution {
    public static void main(String[] args)
    {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        editUserView.setController(controller);

        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);
        usersView.fireEventShowAllUsers();
        usersView.fireEventShowDeletedUsers();
        usersView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(126);
        editUserView.fireEventUserChanged("Ihor", 123, 3);
    }
}
