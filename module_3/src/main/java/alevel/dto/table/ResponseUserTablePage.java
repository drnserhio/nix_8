package alevel.dto.table;

import alevel.model.impl.User;
import lombok.Data;

import java.util.List;

@Data
public class ResponseUserTablePage {

    private List<User> users;
    private int page;
    private int totalPages;
    private int showEntity;
    private int allSizeEntity;
    private String sort;
    private int showEntityTo;
    private int showEntityFrom;
}
