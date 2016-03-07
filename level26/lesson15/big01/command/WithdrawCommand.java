package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String CurrencyCode=ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(CurrencyCode);
        int amount;

        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            amount=Integer.parseInt(ConsoleHelper.readString());
            if (amount < 0) ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            if(manipulator.isAmountAvailable(amount))break;
            else ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }
        try {
            Map<Integer, Integer> map=manipulator.withdrawAmount(amount);
            for (Map.Entry<Integer, Integer> pair :map.entrySet())
                ConsoleHelper.writeMessage("\t"+pair.getKey()+" - "+pair.getValue());

            ConsoleHelper.writeMessage(res.getString("success.format"));
        }
        catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
        }

    }
}
