package com.javarush.test.level39.lesson09.big01.query;

import com.javarush.test.level39.lesson09.big01.Event;

import java.util.Date;
import java.util.Set;

public interface DateQuery {
    Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before);
    Set<Date> getDatesWhenSomethingFailed(Date after, Date before);
    Set<Date> getDatesWhenErrorHappened(Date after, Date before);
    Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before);
    Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before);
    Date getDateWhenUserDoneTask(String user, int task, Date after, Date before);
    Set<Date>  getDatesWhenUserWroteMessage(String user, Date after, Date before);
    Set<Date>  getDatesWhenUserDownloadedPlugin(String user, Date after, Date before);
}