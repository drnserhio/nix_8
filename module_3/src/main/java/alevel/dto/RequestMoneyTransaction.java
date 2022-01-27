package alevel.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class RequestMoneyTransaction {
    private Long senderId;
    private Long recipientId;
    private Long acccountSenderId;
    private Long accountRecipientId;
    private Long summa;
}
