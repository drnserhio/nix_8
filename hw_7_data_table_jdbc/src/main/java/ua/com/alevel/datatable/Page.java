package ua.com.alevel.datatable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Page<T> {

    private List<T> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;

    public Page(List<T> data) {
        this.data = data;
    }


}
