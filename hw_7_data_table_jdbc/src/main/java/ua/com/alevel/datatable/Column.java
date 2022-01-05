package ua.com.alevel.datatable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Column {

    private String data;
    private String name;
    private Boolean searchabel;
    private Boolean orderable;
    private Search search;

    public Column(String data) {
        this.data = data;
    }
}
