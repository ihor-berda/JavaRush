package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
{
    private Path logDir;
    public LogParser(Path logDir)
    {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        int count = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> ipDateMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                if (matcherIp.find() && matcherDate.find()) ipDateMap.put(matcherDate.group(), matcherIp.group());
            }
            ArrayList<String> ips = new ArrayList<>();
            for (Map.Entry<String, String> entry : ipDateMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !ips.contains(entry.getValue())) {
                            count++;
                            flag = true;
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !ips.contains(entry.getValue())) {
                            count++;
                            flag = true;
                        }
                    if (after == null && before == null && !ips.contains(entry.getValue())) {
                        count++;
                        flag = true;
                    }
                    if (after != null && before != null && !ips.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            count++;
                            flag = true;
                        }
                    }

                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                if (flag) ips.add(entry.getValue());
            }
        }
        return count;
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueIps = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> ipDateMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                if (matcherIp.find() && matcherDate.find()) ipDateMap.put(matcherDate.group(), matcherIp.group());
            }
            for (Map.Entry<String, String> entry : ipDateMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueIps.contains(entry.getValue())) {
                            flag = true;
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueIps.contains(entry.getValue())) {
                            flag = true;
                        }
                    if (after == null && before == null && !uniqueIps.contains(entry.getValue())) {
                        flag = true;
                    }
                    if (after != null && before != null && !uniqueIps.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            flag = true;
                        }
                    }

                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueIps.add(entry.getValue());
            }
        }
        return uniqueIps;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueIps = new HashSet<>();
        Set<String> userWorkWithIps = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateIpMap = new HashMap<>();
            Map<String, String> dateNameMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherIp.find() && matcherDate.find() && matcherName.find()) {
                    dateIpMap.put(matcherDate.group(), matcherIp.group());
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                }
            }
            for (Map.Entry<String, String> entry : dateIpMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before == null && !uniqueIps.contains(entry.getValue())) {
                        if (dateNameMap.get(entry.getKey()).equals(user)) {
                            userWorkWithIps.add(entry.getValue());
                            flag = true;
                        }
                    }
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueIps.contains(entry.getValue())) {
                            if (dateNameMap.get(entry.getKey()).equals(user)) {
                                userWorkWithIps.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueIps.contains(entry.getValue())) {
                            if (dateNameMap.get(entry.getKey()).equals(user)) {
                                userWorkWithIps.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (after != null && before != null && !uniqueIps.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateNameMap.get(entry.getKey()).equals(user)) {
                                userWorkWithIps.add(entry.getValue());
                                flag = true;
                            }
                        }
                    }

                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueIps.add(entry.getValue());
            }
        }
        return userWorkWithIps;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueIps = new HashSet<>();
        Set<String> ipsForEvent = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateIpMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                if (matcherIp.find() && matcherDate.find() && matcherEvent.find()) {
                    dateIpMap.put(matcherDate.group(), matcherIp.group());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateIpMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueIps.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(event.name())) {
                                ipsForEvent.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueIps.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(event.name())) {
                                ipsForEvent.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (after == null && before == null && !uniqueIps.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(event.name())) {
                            ipsForEvent.add(entry.getValue());
                            flag = true;
                        }
                    }
                    if (after != null && before != null && !uniqueIps.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(event.name())) {
                                ipsForEvent.add(entry.getValue());
                                flag = true;
                            }
                        }
                    }


                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueIps.add(entry.getValue());
            }
        }
        return ipsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> ipsForStatus = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                if (matcherIp.find() && matcherDate.find()) {
                    String statusString = test.substring(test.length() - status.name().length());
                    try {
                        Date date = formatter.parse(matcherDate.group());
                        if (after == null && before != null) {
                            if (date.before(before) || date.equals(before)) {
                                if (statusString.equals(status.name())) {
                                    ipsForStatus.add(matcherIp.group());
                                }
                            }
                        }
                        if (before == null && after != null) {
                            if ((date.after(after) || date.equals(after))) {
                                if (statusString.equals(status.name())) {
                                    ipsForStatus.add(matcherIp.group());
                                }
                            }
                        }
                        if (after == null && before == null) {
                            if (statusString.equals(status.name())) {
                                ipsForStatus.add(matcherIp.group());
                            }
                        }
                        if (after != null && before != null) {
                            if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                                if (statusString.equals(status.name())) {
                                    ipsForStatus.add(matcherIp.group());
                                }
                            }
                        }
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ipsForStatus;
    }

    @Override
    public Set<String> getAllUsers()
    {
        Set<String> users = new HashSet<>();
        Set<String> uniqueUsers = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherName.find()) {
                    users.add(matcherName.group().trim());
                }
            }
            for (String name : users) {
                if (!uniqueUsers.contains(name)) {
                    uniqueUsers.add(name);
                }
            }
        }
        return uniqueUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int count = 0;
        Set<String> uniqueNames = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        flag = true;
                        count++;
                    }
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            flag = true;
                            count++;
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            flag = true;
                            count++;
                        }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            flag = true;
                            count++;
                        }
                    }

                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return count;
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int count = 0;
        Set<String> events = new HashSet<>();
        for (File file : logDir.toFile().listFiles())
        {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, String> dateNameMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile()))
            {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            for (String test : data)
            {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                if (matcherEvent.find() && matcherDate.find() && matcherName.find())
                {
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet())
            {
                boolean flag = false;
                try
                {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before == null && !events.contains(entry.getValue()))
                    {
                        if (dateNameMap.get(entry.getKey()).equals(user))
                        {
                            flag = true;
                            count++;
                        }
                    }
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !events.contains(entry.getValue()))
                        {
                            if (dateNameMap.get(entry.getKey()).equals(user))
                            {
                                flag = true;
                                count++;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !events.contains(entry.getValue()))
                        {
                            if (dateNameMap.get(entry.getKey()).equals(user))
                            {
                                flag = true;
                                count++;
                            }
                        }
                    if (after != null && before != null && !events.contains(entry.getValue()))
                    {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before))
                        {
                            if (dateNameMap.get(entry.getKey()).equals(user))
                            {
                                flag = true;
                                count++;
                            }
                        }
                    }

                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                if (flag) events.add(entry.getValue());
            }
        }
        return count;
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueUsers = new HashSet<>();
        Set<String> usersForIp = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateIpMap = new HashMap<>();
            Map<String, String> dateNameMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherIp.find() && matcherDate.find() && matcherName.find()) {
                    dateIpMap.put(matcherDate.group(), matcherIp.group());
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before == null && !uniqueUsers.contains(entry.getValue())) {
                        if (dateIpMap.get(entry.getKey()).equals(ip)) {
                            usersForIp.add(entry.getValue());
                            flag = true;
                        }
                    }
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueUsers.contains(entry.getValue())) {
                            if (dateIpMap.get(entry.getKey()).equals(ip)) {
                                usersForIp.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueUsers.contains(entry.getValue())) {
                            if (dateIpMap.get(entry.getKey()).equals(ip)) {
                                usersForIp.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (after != null && before != null && !uniqueUsers.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateIpMap.get(entry.getKey()).equals(ip)) {
                                usersForIp.add(entry.getValue());
                                flag = true;
                            }
                        }
                    }

                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueUsers.add(entry.getValue());
            }
        }
        return usersForIp;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> loggedUsers = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name())) {
                                    loggedUsers.add(entry.getValue());
                                    flag = true;
                                }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name())) {
                                    loggedUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name())) {
                                loggedUsers.add(entry.getValue());
                                flag = true;
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name())) {
                                    loggedUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return loggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> downloadPluginUsers = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name())) {
                                    downloadPluginUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name())) {
                                    downloadPluginUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name())) {
                                downloadPluginUsers.add(entry.getValue());
                                flag = true;
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name())) {
                                    downloadPluginUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return downloadPluginUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> wroteMessageUsers = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name())) {
                                wroteMessageUsers.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name())) {
                                wroteMessageUsers.add(entry.getValue());
                                flag = true;
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name())) {
                                wroteMessageUsers.add(entry.getValue());
                                flag = true;
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name())) {
                                    wroteMessageUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return wroteMessageUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> solvedTaskUsers = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                    solvedTaskUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                    solvedTaskUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                solvedTaskUsers.add(entry.getValue());
                                flag = true;
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                    solvedTaskUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> solvedTaskUsersWithNumber = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                if (dateNumberMap.get(entry.getKey()) == task) {
                                        solvedTaskUsersWithNumber.add(entry.getValue());
                                        flag = true;
                                }
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                if (dateNumberMap.get(entry.getKey()) == task) {
                                        solvedTaskUsersWithNumber.add(entry.getValue());
                                        flag = true;
                                }
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                            if (dateNumberMap.get(entry.getKey()) == task) {
                                    solvedTaskUsersWithNumber.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name())) {
                                if (dateNumberMap.get(entry.getKey()) == task) {
                                        solvedTaskUsersWithNumber.add(entry.getValue());
                                        flag = true;
                                }
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return solvedTaskUsersWithNumber;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> doneTaskUsers = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
//            Map<String, String> dateStatusMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                    doneTaskUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                    doneTaskUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                doneTaskUsers.add(entry.getValue());
                                flag = true;
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                    doneTaskUsers.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return doneTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueNames = new HashSet<>();
        Set<String> doneTaskUsersWithNumber = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                if (dateNumberMap.get(entry.getKey()) == task) {
                                        doneTaskUsersWithNumber.add(entry.getValue());
                                        flag = true;
                                }
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after)) && !uniqueNames.contains(entry.getValue())) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                if (dateNumberMap.get(entry.getKey()) == task) {
                                        doneTaskUsersWithNumber.add(entry.getValue());
                                        flag = true;
                                }
                            }
                        }
                    if (after == null && before == null && !uniqueNames.contains(entry.getValue())) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                            if (dateNumberMap.get(entry.getKey()) == task) {
                                    doneTaskUsersWithNumber.add(entry.getValue());
                                    flag = true;
                            }
                        }
                    }
                    if (after != null && before != null && !uniqueNames.contains(entry.getValue())) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name())) {
                                if (dateNumberMap.get(entry.getKey()) == task) {
                                        doneTaskUsersWithNumber.add(entry.getValue());
                                        flag = true;
                                }
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueNames.add(entry.getValue());
            }
        }
        return doneTaskUsersWithNumber;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Date> dateForUserAndEvent = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateEventMap.get(entry.getKey()).equals(event.name()) && entry.getValue().equals(user)) {
                                dateForUserAndEvent.add(date);
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateEventMap.get(entry.getKey()).equals(event.name()) && entry.getValue().equals(user)) {
                                dateForUserAndEvent.add(date);
                            }
                        }
                    if (after == null && before == null) {
                        if (dateEventMap.get(entry.getKey()).equals(event.name()) && entry.getValue().equals(user)) {
                            dateForUserAndEvent.add(date);
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(event.name()) && entry.getValue().equals(user)) {
                                dateForUserAndEvent.add(date);
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return dateForUserAndEvent;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Date> dateWhenFailed = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateStatusMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                if (matcherDate.find() && matcherEvent.find()) {
                    String status = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\d","").trim();
                    dateStatusMap.put(matcherDate.group(), status);
                }
            }
            for (Map.Entry<String, String> entry : dateStatusMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (entry.getValue().equals(Status.FAILED.name())) {
                                dateWhenFailed.add(date);
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (entry.getValue().equals(Status.FAILED.name())) {
                                dateWhenFailed.add(date);
                            }
                        }
                    if (after == null && before == null) {
                        if (entry.getValue().equals(Status.FAILED.name())) {
                            dateWhenFailed.add(date);
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (entry.getValue().equals(Status.FAILED.name())) {
                                dateWhenFailed.add(date);
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return dateWhenFailed;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Date> dateWhenError = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateStatusMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                if (matcherDate.find() && matcherEvent.find()) {
                    String status = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\d","").trim();
                    dateStatusMap.put(matcherDate.group(), status);
                }
            }
            for (Map.Entry<String, String> entry : dateStatusMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (entry.getValue().equals(Status.ERROR.name())) {
                                dateWhenError.add(date);
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (entry.getValue().equals(Status.ERROR.name())) {
                                dateWhenError.add(date);
                            }
                        }
                    if (after == null && before == null) {
                        if (entry.getValue().equals(Status.ERROR.name())) {
                            dateWhenError.add(date);
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (entry.getValue().equals(Status.ERROR.name())) {
                                dateWhenError.add(date);
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return dateWhenError;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    try {
                        return formatter.parse(o1).compareTo(formatter.parse(o2));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                };
            });
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name()) && entry.getValue().equals(user)) {
                                return date;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name()) && entry.getValue().equals(user)) {
                                return date;
                            }
                        }
                    if (after == null && before == null) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name()) && entry.getValue().equals(user)) {
                            return date;
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.LOGIN.name()) && entry.getValue().equals(user)) {
                                return date;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    try {
                        return formatter.parse(o1).compareTo(formatter.parse(o2));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                };
            });
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                                return date;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                                return date;
                            }
                        }
                    if (after == null && before == null) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                            return date;
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.SOLVE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                                return date;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    try {
                        return formatter.parse(o1).compareTo(formatter.parse(o2));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                };
            });
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                                return date;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                                return date;
                            }
                        }
                    if (after == null && before == null) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                            return date;
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DONE_TASK.name()) && entry.getValue().equals(user) && dateNumberMap.get(entry.getKey()) == task) {
                                return date;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Date> wroteMessageDates = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name()) && entry.getValue().equals(user)) {
                                wroteMessageDates.add(date);
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name()) && entry.getValue().equals(user)) {
                                wroteMessageDates.add(date);
                            }
                        }
                    if (after == null && before == null) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name()) && entry.getValue().equals(user)) {
                            wroteMessageDates.add(date);
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.WRITE_MESSAGE.name()) && entry.getValue().equals(user)) {
                                wroteMessageDates.add(date);
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return wroteMessageDates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Date> downloadPluginDates = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateNameMap = new HashMap<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateNameMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name()) && entry.getValue().equals(user)) {
                                downloadPluginDates.add(date);
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name()) && entry.getValue().equals(user)) {
                                downloadPluginDates.add(date);
                            }
                        }
                    if (after == null && before == null) {
                        if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name()) && entry.getValue().equals(user)) {
                            downloadPluginDates.add(date);
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateEventMap.get(entry.getKey()).equals(Event.DOWNLOAD_PLUGIN.name()) && entry.getValue().equals(user)) {
                                downloadPluginDates.add(date);
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return downloadPluginDates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<String> uniqueEvents = new HashSet<>();
        int count = 0;
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                if (matcherDate.find() && matcherEvent.find()) {
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (!uniqueEvents.contains(entry.getValue())) {
                                count++;
                                flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (!uniqueEvents.contains(entry.getValue())) {
                                count++;
                                flag = true;
                            }
                        }
                    if (after == null && before == null) {
                        if (!uniqueEvents.contains(entry.getValue())) {
                            count++;
                            flag = true;
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (!uniqueEvents.contains(entry.getValue())) {
                                count++;
                                flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueEvents.add(entry.getValue());
            }
        }
        return count;
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Event> uniqueEvents = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                if (matcherDate.find() && matcherEvent.find()) {
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                boolean flag = false;
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (!uniqueEvents.contains(Event.valueOf(entry.getValue()))) {
                                flag = true;
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (!uniqueEvents.contains(Event.valueOf(entry.getValue()))) {
                                flag = true;
                            }
                        }
                    if (after == null && before == null) {
                        if (!uniqueEvents.contains(Event.valueOf(entry.getValue()))) {
                            flag = true;
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (!uniqueEvents.contains(Event.valueOf(entry.getValue()))) {
                                flag = true;
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                if (flag) uniqueEvents.add(Event.valueOf(entry.getValue()));
            }
        }
        return uniqueEvents;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Event> eventsForIp = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, String> dateIpMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                Matcher matcherIp = ipPattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherIp.find()) {
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateIpMap.put(matcherDate.group(), matcherIp.group());
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateIpMap.get(entry.getKey()).equals(ip)) eventsForIp.add(Event.valueOf(entry.getValue()));
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateIpMap.get(entry.getKey()).equals(ip)) eventsForIp.add(Event.valueOf(entry.getValue()));
                        }
                    if (after == null && before == null) {
                        if (dateIpMap.get(entry.getKey()).equals(ip)) eventsForIp.add(Event.valueOf(entry.getValue()));
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateIpMap.get(entry.getKey()).equals(ip)) eventsForIp.add(Event.valueOf(entry.getValue()));
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return eventsForIp;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Event> eventsForUser = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, String> dateNameMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNameMap.put(matcherDate.group(), matcherName.group().trim());
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateNameMap.get(entry.getKey()).equals(user)) eventsForUser.add(Event.valueOf(entry.getValue()));
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateNameMap.get(entry.getKey()).equals(user)) eventsForUser.add(Event.valueOf(entry.getValue()));
                        }
                    if (after == null && before == null) {
                        if (dateNameMap.get(entry.getKey()).equals(user)) eventsForUser.add(Event.valueOf(entry.getValue()));
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateNameMap.get(entry.getKey()).equals(user)) eventsForUser.add(Event.valueOf(entry.getValue()));
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return eventsForUser;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Event> failedEvents = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, String> dateStatusMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String status = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\d","").trim();
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateStatusMap.put(matcherDate.group(), status);
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateStatusMap.get(entry.getKey()).equals(Status.FAILED.name())) failedEvents.add(Event.valueOf(entry.getValue()));
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateStatusMap.get(entry.getKey()).equals(Status.FAILED.name())) failedEvents.add(Event.valueOf(entry.getValue()));
                        }
                    if (after == null && before == null) {
                        if (dateStatusMap.get(entry.getKey()).equals(Status.FAILED.name())) failedEvents.add(Event.valueOf(entry.getValue()));
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateStatusMap.get(entry.getKey()).equals(Status.FAILED.name())) failedEvents.add(Event.valueOf(entry.getValue()));
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return failedEvents;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Set<Event> errorEvents = new HashSet<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, String> dateStatusMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String status = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\d","").trim();
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateStatusMap.put(matcherDate.group(), status);
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (dateStatusMap.get(entry.getKey()).equals(Status.ERROR.name())) errorEvents.add(Event.valueOf(entry.getValue()));
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (dateStatusMap.get(entry.getKey()).equals(Status.ERROR.name())) errorEvents.add(Event.valueOf(entry.getValue()));
                        }
                    if (after == null && before == null) {
                        if (dateStatusMap.get(entry.getKey()).equals(Status.ERROR.name())) errorEvents.add(Event.valueOf(entry.getValue()));
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (dateStatusMap.get(entry.getKey()).equals(Status.ERROR.name())) errorEvents.add(Event.valueOf(entry.getValue()));
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return errorEvents;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int count = 0;
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (entry.getValue().equals(Event.SOLVE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (entry.getValue().equals(Event.SOLVE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                        }
                    if (after == null && before == null) {
                        if (entry.getValue().equals(Event.SOLVE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (entry.getValue().equals(Event.SOLVE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int count = 0;
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (entry.getValue().equals(Event.DONE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (entry.getValue().equals(Event.DONE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                        }
                    if (after == null && before == null) {
                        if (entry.getValue().equals(Event.DONE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (entry.getValue().equals(Event.DONE_TASK.name()) && dateNumberMap.get(entry.getKey()) == task) count++;
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> numberSolveTask = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    int count = 0;
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (entry.getValue().equals(Event.SOLVE_TASK.name())) {
                                count++;
                                if (!numberSolveTask.containsKey(dateNumberMap.get(entry.getKey())))
                                    numberSolveTask.put(dateNumberMap.get(entry.getKey()), count);
                                else {
                                    int newValue = numberSolveTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                    numberSolveTask.put(dateNumberMap.get(entry.getKey()), newValue);
                                }
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (entry.getValue().equals(Event.SOLVE_TASK.name())) {
                                count++;
                                if (!numberSolveTask.containsKey(dateNumberMap.get(entry.getKey())))
                                    numberSolveTask.put(dateNumberMap.get(entry.getKey()), count);
                                else {
                                    int newValue = numberSolveTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                    numberSolveTask.put(dateNumberMap.get(entry.getKey()), newValue);
                                }
                            }
                        }
                    if (after == null && before == null) {
                        if (entry.getValue().equals(Event.SOLVE_TASK.name())) {
                            count++;
                            if (!numberSolveTask.containsKey(dateNumberMap.get(entry.getKey())))
                                numberSolveTask.put(dateNumberMap.get(entry.getKey()), count);
                            else {
                                int newValue = numberSolveTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                numberSolveTask.put(dateNumberMap.get(entry.getKey()), newValue);
                            }
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (entry.getValue().equals(Event.SOLVE_TASK.name())) {
                                count++;
                                if (!numberSolveTask.containsKey(dateNumberMap.get(entry.getKey())))
                                    numberSolveTask.put(dateNumberMap.get(entry.getKey()), count);
                                else {
                                    int newValue = numberSolveTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                    numberSolveTask.put(dateNumberMap.get(entry.getKey()), newValue);
                                }
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return numberSolveTask;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        Map<Integer, Integer> numberDoneTask = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            Map<String, String> dateEventMap = new HashMap<>();
            Map<String, Integer> dateNumberMap = new HashMap<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                Matcher matcherDate = datePattern.matcher(test);
                Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                Matcher matcherEvent = eventPatter.matcher(test);
                Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                Matcher matcherName = namePattern.matcher(test);
                if (matcherDate.find() && matcherEvent.find() && matcherName.find()) {
                    String stringNumber = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\D","").trim();
                    int number = 0;
                    if (!stringNumber.equals("")) number = Integer.parseInt(stringNumber);
                    dateEventMap.put(matcherDate.group(), matcherEvent.group());
                    dateNumberMap.put(matcherDate.group(), number);
                }
            }
            for (Map.Entry<String, String> entry : dateEventMap.entrySet()) {
                try {
                    int count = 0;
                    Date date = formatter.parse(entry.getKey());
                    if (after == null && before != null)
                        if ((date.before(before) || date.equals(before))) {
                            if (entry.getValue().equals(Event.DONE_TASK.name())) {
                                count++;
                                if (!numberDoneTask.containsKey(dateNumberMap.get(entry.getKey())))
                                    numberDoneTask.put(dateNumberMap.get(entry.getKey()), count);
                                else {
                                    int newValue = numberDoneTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                    numberDoneTask.put(dateNumberMap.get(entry.getKey()), newValue);
                                }
                            }
                        }
                    if (before == null && after != null)
                        if ((date.after(after) || date.equals(after))) {
                            if (entry.getValue().equals(Event.DONE_TASK.name())) {
                                count++;
                                if (!numberDoneTask.containsKey(dateNumberMap.get(entry.getKey())))
                                    numberDoneTask.put(dateNumberMap.get(entry.getKey()), count);
                                else {
                                    int newValue = numberDoneTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                    numberDoneTask.put(dateNumberMap.get(entry.getKey()), newValue);
                                }
                            }
                        }
                    if (after == null && before == null) {
                        if (entry.getValue().equals(Event.DONE_TASK.name())) {
                            count++;
                            if (!numberDoneTask.containsKey(dateNumberMap.get(entry.getKey())))
                                numberDoneTask.put(dateNumberMap.get(entry.getKey()), count);
                            else {
                                int newValue = numberDoneTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                numberDoneTask.put(dateNumberMap.get(entry.getKey()), newValue);
                            }
                        }
                    }
                    if (after != null && before != null) {
                        if ((date.after(after) && date.before(before)) || date.equals(after) || date.equals(before)) {
                            if (entry.getValue().equals(Event.DONE_TASK.name())) {
                                count++;
                                if (!numberDoneTask.containsKey(dateNumberMap.get(entry.getKey())))
                                    numberDoneTask.put(dateNumberMap.get(entry.getKey()), count);
                                else {
                                    int newValue = numberDoneTask.get(dateNumberMap.get(entry.getKey())) + 1;
                                    numberDoneTask.put(dateNumberMap.get(entry.getKey()), newValue);
                                }
                            }
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return numberDoneTask;
    }

    @Override
    public Set<Object> execute(String query) {
        Pattern pattern = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?"
                + "( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher matcher = pattern.matcher(query);
        Set<Object> result = new HashSet<>();
        if (matcher.find()) {
            String field1 = null;
            if (matcher.group(1) != null) field1 = matcher.group(1);
            String field2 = null;
            if (matcher.group(3) != null) field2 = matcher.group(3);
            String value = null;
            if (matcher.group(4) != null) value = matcher.group(4);
            String afterString = null;
            if (matcher.group(6) != null) afterString = matcher.group(6);
            String beforeString = null;
            if (matcher.group(7) != null) beforeString = matcher.group(7);
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date after = null;
            Date before = null;
            try {
                if (afterString != null) after = formatter.parse(afterString);
                if (beforeString != null) before = formatter.parse(beforeString);
            }
            catch (ParseException e) {}

            LogRecord logRecord = new LogRecord();
            logRecord.fill();
            List<String> filteredLogs = new ArrayList<>();
            if (value != null) filteredLogs = getFilteredLogs(after, before, value);
            if (field1.equals("ip")) {
                Set<String> allIps = logRecord.getIps();
                if (field2 == null) {
                    Set<Object> ips = new HashSet<>();
                    ips.addAll(allIps);
                    return ips;
                }
                for (String string : filteredLogs){
                    for (String s : allIps){
                        if (string.contains(s)) result.add(s);
                    }
                }
            }
            else if (field1.equals("user")) {
                Set<String> allUsers = logRecord.getUsers();
                if (field2 == null) {
                    Set<Object> users = new HashSet<>();
                    users.addAll(allUsers);
                    return users;
                }
                for (String string : filteredLogs) {
                    Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                    Matcher matcherName = namePattern.matcher(string);
                    if (matcherName.find()) {
                        for (String s : allUsers) if (matcherName.group().trim().equals(s)) result.add(s);
                    }
                }
            }
            else if (field1.equals("date")) {
                Set<Date> allDates = logRecord.getDates();
                if (field2 == null) {
                    Set<Object> dates = new HashSet<>();
                    dates.addAll(allDates);
                    return dates;
                }
                for (String string : filteredLogs) {
                    for (Date d : allDates) if (string.contains(formatter.format(d))) result.add(d);
                }
            }
            else if (field1.equals("event")) {
                Set<Event> allEvents = logRecord.getEvents();
                if (field2 == null) {
                    Set<Object> events = new HashSet<>();
                    events.addAll(allEvents);
                    return events;
                }
                for (String string : filteredLogs) {
                    for (Event e : allEvents){
                        if (string.contains(e.toString())) result.add(e);
                    }
                }
            }
            else if (field1.equals("status")) {
                Set<Status> allEvents = logRecord.getStatuses();
                if (field2 == null) {
                    Set<Object> statuses = new HashSet<>();
                    statuses.addAll(allEvents);
                    return statuses;
                }
                for (String string : filteredLogs) {
                    for (Status s : allEvents){
                        if (string.contains(s.toString())) result.add(s);
                    }
                }
            }
        }
        return result;
    }
    private List<String> getFilteredLogs(Date after, Date before, String filter) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        List<String> filteredLogs = new ArrayList<>();
        for (File file : logDir.toFile().listFiles()) {
            ArrayList<String> data = new ArrayList<>();
            try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                while (scanner.hasNext()) data.add(scanner.nextLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for (String test : data) {
                if (test.contains(filter)) {
                    if (after == null && before == null) filteredLogs.add(test);
                    if (after != null && before != null) {
                        Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                        Matcher matcherDate = datePattern.matcher(test);
                        if (matcherDate.find()) {
                            try {
                                Date current = formatter.parse(matcherDate.group());
                                if (current.after(after) && current.before(before) || current.equals(after) || current.equals(before)) filteredLogs.add(test);
                            }
                            catch (ParseException e) {}
                        }
                    }
                }
            }
        }
        return filteredLogs;
    }
    private class LogRecord {
        private Set<String> ips = new HashSet<>();
        private Set<String> users = new HashSet<>();
        private Set<Date> dates = new HashSet<>();
        private Set<Event> events = new HashSet<>();
        private Set<Status> statuses = new HashSet<>();
        public Set<Date> getDates()
        {
            return dates;
        }
        public Set<Event> getEvents()
        {
            return events;
        }
        public Set<String> getIps()
        {
            return ips;
        }
        public Set<Status> getStatuses()
        {
            return statuses;
        }
        public Set<String> getUsers()
        {
            return users;
        }
        public void fill() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            for (File file : logDir.toFile().listFiles()) {
                ArrayList<String> data = new ArrayList<>();
                try (Scanner scanner = new Scanner(file.getAbsoluteFile())) {
                    while (scanner.hasNext()) data.add(scanner.nextLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                for (String test : data) {
                    Pattern datePattern = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})\\s(\\d+):(\\d+):(\\d+)");
                    Matcher matcherDate = datePattern.matcher(test);
                    Pattern eventPatter = Pattern.compile("([A-Z]+)_?([A-Z]+)");
                    Matcher matcherEvent = eventPatter.matcher(test);
                    Pattern namePattern = Pattern.compile("(\\D+\\s)+");
                    Matcher matcherName = namePattern.matcher(test);
                    Pattern ipPattern = Pattern.compile("(\\d+).(\\d+).(\\d+).(\\d+)");
                    Matcher matcherIp = ipPattern.matcher(test);
                    if (matcherIp.find()) ips.add(matcherIp.group());
                    if (matcherName.find()) users.add(matcherName.group().trim());
                    if (matcherDate.find()){
                        try {
                            dates.add(formatter.parse(matcherDate.group()));
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (matcherEvent.find()){
                        events.add(Event.valueOf(matcherEvent.group()));
                        String status = test.substring(test.indexOf(matcherEvent.group()) + matcherEvent.group().length()).replaceAll("\\d","").trim();
                        statuses.add(Status.valueOf(status));
                    }
                }
            }
        }
    }
}
