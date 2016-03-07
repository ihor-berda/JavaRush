package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;
import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean hasMoney() {
        boolean result = true;
        if (denominations.isEmpty()) {
            result = false;
        }
        else {
            int count = 0;
            for (Map.Entry<Integer, Integer> map : denominations.entrySet()) {
                if (map.getValue() == 0) {
                    count++;
                }
            }
            if (count == denominations.size()) {
                result = false;
            }
        }
        return result;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        if (getTotalAmount() >= expectedAmount) return true;
        else return false;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
        else { denominations.put(denomination, count); }
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> money : denominations.entrySet()) {
            amount = amount + (money.getKey() * money.getValue());
        }
        return amount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        ArrayList<Integer> currentDenominations = new ArrayList<>(denominations.keySet());
        Collections.sort(currentDenominations, Collections.reverseOrder());
        Map<Integer, Integer> copyDenomination = new HashMap<>();
        copyDenomination.putAll(denominations);

        for (int i = 0; i < currentDenominations.size() ; i++) {
            if (expectedAmount > 0) {
                int value = currentDenominations.get(i);
                if (expectedAmount >= value) {
                    int count = expectedAmount / value;
                    if (denominations.get(value) > count) {
                        result.put(value, count);
                        denominations.put(value, denominations.get(value) - count);
                        expectedAmount = expectedAmount - count * value;
                    } else {
                        result.put(value, denominations.get(value));
                        expectedAmount = expectedAmount - denominations.get(value) * value;
                        denominations.remove(value);
                    }
                }
            }
            else break;
        }
        if (expectedAmount != 0) {
            denominations = copyDenomination;
            throw new NotEnoughMoneyException();
        }
        return result;
    }

}
