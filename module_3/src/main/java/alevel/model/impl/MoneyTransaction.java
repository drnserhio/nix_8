package alevel.model.impl;

import alevel.model.impl.Account;
import alevel.model.impl.Operation;
import lombok.Data;

@Data
public class MoneyTransaction {

    private Account senderUpdate;
    private Account recipientUpdate;
    private Operation operationSender;
    private Operation operationRecipient;
}
