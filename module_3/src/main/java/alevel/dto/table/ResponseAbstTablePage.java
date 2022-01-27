package alevel.dto.table;

import lombok.Data;

import java.util.List;

@Data
public abstract class ResponseAbstTablePage<T> {

    private List<T> content;
    private int page;
    private int totalPages;
    private int showEntity;
    private int allSizeEntity;
    private String sort;
    private int showEntityTo;
    private int showEntityFrom;
}
