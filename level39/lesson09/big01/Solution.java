package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("E:\\JavaRushHomeWork\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs\\"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String afterString = "13.09.2015 5:04:50";
        String beforeString = "13.09.2072 05:04:50";
        try
        {
            Date after = simpleDateFormat.parse(afterString);
            Date before = null;
//            System.out.println(logParser.getNumberOfUniqueIPs(after, before));
//
//            System.out.println(logParser.getUniqueIPs(after, before));
//
//            System.out.println(logParser.getIPsForUser("Vasya Pupkin", after, before));
//
//            System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, after, before));
//
//            System.out.println(logParser.getIPsForStatus(Status.OK, after, before));
//
//
//
//            System.out.println(logParser.getAllUsers());
//
//            System.out.println(logParser.getNumberOfUsers(after, before));
//
//            System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", after, before));
//
//            System.out.println(logParser.getUsersForIP("192.168.100.2", after, before));
//
//            System.out.println(logParser.getLoggedUsers(after, before)); // TESTED
//
//            System.out.println(logParser.getDownloadedPluginUsers(after, before)); // TESTED
//
//            System.out.println(logParser.getWroteMessageUsers(after, before)); // TESTED
//
//            System.out.println(logParser.getSolvedTaskUsers(after, before)); // TESTED
//
//            System.out.println(logParser.getSolvedTaskUsers(after, before, 18)); // TESTED
//
//            System.out.println(logParser.getDoneTaskUsers(after, before)); // TESTED
//
//            System.out.println(logParser.getDoneTaskUsers(after, before, 15)); // TESTED
//
//            System.out.println(logParser.getDatesForUserAndEvent("Vasya Pupkin", Event.SOLVE_TASK, after, before)); // TESTED twice
//
//            System.out.println(logParser.getDatesWhenSomethingFailed(after, before)); // TESTED twice
//
//            System.out.println(logParser.getDatesWhenErrorHappened(after, before)); // TESTED
//
//            System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 18, after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin", 15, after, before)); // TESTED (w/o status) ? first time?
//
//            System.out.println(logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getNumberOfAllEvents(after, before)); // TESTED
//
//            System.out.println(logParser.getAllEvents(after, before)); // TESTED
//
//            System.out.println(logParser.getEventsForIP("146.34.15.5", after, before)); // TESTED
//
//            System.out.println(logParser.getEventsForUser("Amigo", after, before)); // TESTED
//
//            System.out.println(logParser.getFailedEvents(after, before)); // TESTED
//
//            System.out.println(logParser.getErrorEvents(after, before)); // TESTED
//
//            System.out.println(logParser.getNumberOfAttemptToSolveTask(18, after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15, after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getAllSolvedTasksAndTheirNumber(after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.getAllDoneTasksAndTheirNumber(after, before)); // TESTED (w/o status)
//
//            System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get ip for date \"13.09.2013 5:04:50\"")); // TESTED works for same dates 3
//
//            System.out.println(logParser.execute("get ip for event = \"LOGIN\"")); // TESTED (event value same as in log) twice
//
//            System.out.println(logParser.execute("get ip for status = \"OK\"")); // TESTED (status value same as in log) twice
//
//            System.out.println(logParser.execute("get user for ip = \"127.0.0.1\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get user for date = \"13.09.2013 5:04:50\"")); // TESTED works for same dates 3
//
//            System.out.println(logParser.execute("get user for event = \"SOLVE_TASK\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get user for status = \"ERROR\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get date for ip = \"12.12.12.12\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get date for user = \"Amigo\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get date for event = \"LOGIN\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get date for status = \"ERROR\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get event for ip = \"146.34.15.5\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\"")); // TESTED  works for same dates 3
//
//            System.out.println(logParser.execute("get event for status = \"FAILED\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get status for ip = \"192.168.100.2\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get status for user = \"Amigo\"")); // TESTED twice
//
//            System.out.println(logParser.execute("get status for date = \"14.11.2015 07:08:01\"")); // TESTED  works for same dates 3
//
//            System.out.println(logParser.execute("get status for event = \"WRITE_MESSAGE\"")); // TESTED twice

            System.out.println(logParser.execute("get ip"));
            System.out.println(logParser.execute("get user"));
            System.out.println(logParser.execute("get date"));
            System.out.println(logParser.execute("get event"));
            System.out.println(logParser.execute("get statuses"));
            System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\""));
            System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get ip for date = \"13.09.2013 5:04:50\""));
            System.out.println(logParser.execute("get ip for date = \"13.09.2013 5:04:50\" and date between \"12.09.2013 10:11:13\\\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get ip for event = \"LOGIN\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get ip for status = \"FAILED\" and date between \"11.12.2013 10:11:11\" and \"11.12.2013 10:11:12\""));
            System.out.println(logParser.execute("get user for ip = \"127.0.0.1\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get user for date = \"13.09.2013 5:04:50\" and date between \"12.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get user for event = \"SOLVE_TASK\" and date between \"14.09.2013 10:11:13\" and \"31.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get user for status = \"ERROR\" and date between \"14.09.2013 10:11:13\" and \"29.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get date for ip = \"12.12.12.12\" and date between \"14.09.2013 10:11:13\" and \"03.01.2022 23:59:59\""));
            System.out.println(logParser.execute("get date for user = \"Amigo\" and date between \"14.09.2011 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get date for event = \"LOGIN\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 03:45:23\""));
            System.out.println(logParser.execute("get date for status = \"ERROR\" and date between \"14.09.2013 10:11:13\" and \"03.01.2015 23:59:59\""));
            System.out.println(logParser.execute("get event for ip = \"146.34.15.5\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get event for user = \"Vasya Pupkin\" and date between \"14.09.2013 10:11:13\" and \"31.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\" and date between \"14.09.2013 10:11:13\" and \"30.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get event for status = \"FAILED\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get status for ip = \"192.168.100.2\" and date between \"12.09.2011 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get status for user = \"Amigo\" and date between \"14.09.2011 10:11:13\" and \"03.01.2014 23:59:59\""));
            System.out.println(logParser.execute("get status for date = \"14.11.2015 07:08:01\" and date between \"14.09.2013 10:11:13\" and \"03.01.2016 23:59:59\""));
            System.out.println(logParser.execute("get status for event = \"WRITE_MESSAGE\" and date between \"14.09.2013 10:11:13\" and \"03.01.2014 23:59:59\""));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }


    }
}
