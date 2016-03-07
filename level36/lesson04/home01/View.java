package com.javarush.test.level36.lesson04.home01;

/**
 * Created by IGOR on 28.01.2016.
 */
public class View
{
    private Controller controller = new Controller();

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
