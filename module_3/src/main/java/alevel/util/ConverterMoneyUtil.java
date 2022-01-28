package alevel.util;

import alevel.exception.MoneyExceptionHandle;
import alevel.model.impl.MoneyTransaction;
import alevel.model.impl.Account;
import alevel.model.impl.Operation;
import alevel.model.impl.User;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDate;

public final class ConverterMoneyUtil {

    private ConverterMoneyUtil() {}

    public static MoneyTransaction uah(User sender, User recipient, Account accountSender, Account accountRecipient, long bring) {
        MoneyTransaction moneyTransaction = new MoneyTransaction();

        if (accountSender.getMoney() - bring < 0) {
            throw new MoneyExceptionHandle("You have little money...(");
        }
        CurrencyUnit uah = Monetary.getCurrency("UAH");
        MonetaryAmount sendSummer = Money.of(accountSender.getMoney(), uah);
        MonetaryAmount sendMinus = Money.of(bring, uah);
        MonetaryAmount changeToSenderMoney = sendSummer.subtract(sendMinus);

        MonetaryAmount recipientMoney = Money.of(accountRecipient.getMoney(), uah);
        MonetaryAmount recipienPlus = Money.of(bring, uah);
        MonetaryAmount chageToRecipient = recipientMoney.add(recipienPlus);

        accountSender.setMoney(changeToSenderMoney.getNumber().longValue());
        accountRecipient.setMoney(chageToRecipient.getNumber().longValue());

        Operation senderOperation = new Operation();
        senderOperation.setDateOperation(LocalDate.now());
        senderOperation.setSender(sender.getUsername());
        senderOperation.setRecipient(recipient.getUsername());
        senderOperation.setOperationFinance(" - " + bring + " from your account. Money now : " + accountSender.getMoney());

        Operation recipientOperation = new Operation();
        recipientOperation.setDateOperation(LocalDate.now());
        recipientOperation.setSender(sender.getUsername());
        recipientOperation.setRecipient(recipient.getUsername());
        recipientOperation.setOperationFinance(" + "  + bring + " from your account. Money now : " + accountRecipient.getMoney());

        moneyTransaction.setSenderUpdate(accountSender);
        moneyTransaction.setRecipientUpdate(accountRecipient);
        moneyTransaction.setOperationSender(senderOperation);
        moneyTransaction.setOperationRecipient(recipientOperation);

        return moneyTransaction;
    }

}
