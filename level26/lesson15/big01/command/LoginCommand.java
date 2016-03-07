package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;
import java.util.Set;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (number.length() != 12 || pin.length() != 4) {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            if (validCreditCards.containsKey(number)) {
                if (validCreditCards.getString(number).equals(pin)) {
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                }
            }
        }
    }
}
