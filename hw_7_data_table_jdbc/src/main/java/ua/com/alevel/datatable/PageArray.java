package ua.com.alevel.datatable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.model.impl.Employee;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageArray {

    private List<Employee> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
}
