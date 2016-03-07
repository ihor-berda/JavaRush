package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {
    private static final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private List<Advertisement> videos = storage.list();
    private int timeSeconds;
    private long maxProfit = 0;
    private int minRemainingTime = timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> bestVariant = new ArrayList<>();
        bestVariant = pickVideosToList(null, null, timeSeconds, 0, bestVariant);
        long totalAmount = 0;
        int totalDuration = 0;
        for (Advertisement ad : bestVariant) {
            ad.revalidate();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(),
                    ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            totalAmount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(bestVariant, totalAmount, totalDuration));
    }
    private List<Advertisement> pickVideosToList(List<Advertisement> previousList, Advertisement previousAd, int remainingTime,
                                                 long profit, List<Advertisement> bestResult) throws NoVideoAvailableException {
        List<Advertisement> newList = new ArrayList<>();
        if (previousList != null) {
            newList.addAll(previousList);
            remainingTime -= previousAd.getDuration();
            profit += previousAd.getAmountPerOneDisplaying();
            newList.add(previousAd);
        }
        for (Advertisement ad : videos) {
            if (remainingTime == 0) break;
            if (newList.contains(ad)) continue;
            if (ad.getHits() <= 0) continue;
            if (remainingTime >= ad.getDuration()) bestResult = pickVideosToList(newList, ad, remainingTime, profit, bestResult);
        }
        if (profit > maxProfit) {
            maxProfit = profit;
            minRemainingTime = remainingTime;
            bestResult = newList;
        } else if (profit == maxProfit && remainingTime < minRemainingTime) {
            minRemainingTime = remainingTime;
            bestResult = newList;
        } else if (profit == maxProfit && remainingTime == minRemainingTime && bestResult.size() > newList.size())
            bestResult = newList;
        if (bestResult.isEmpty()) {
            StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }
        Collections.sort(bestResult, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long pricePerVideoDiff = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (pricePerVideoDiff != 0)
                    return (int) pricePerVideoDiff;
                else
                    return (int) (o1.getAmountPerOneDisplaying() * 100 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 100 / o2.getDuration());
            }
        });
        return bestResult;
    }
}