package ua.com.alevel.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.alevel.datatable.*;
import ua.com.alevel.model.impl.Employee;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PagingService {


    private static final Comparator<Employee> EMPTY_COMPARATOR = (e1, e2) -> 0;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PageArray getEmployeesArray(PagingRequest pagingRequest, List<Employee> employees) {
        pagingRequest.setColumns(Stream.of("id", "dateCreate", "dateupdate", "firstname", "lastname", "username")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<Employee> employeePage = getEmployees(pagingRequest, employees);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(employeePage.getRecordsFiltered());
        pageArray.setRecordsTotal(employeePage.getRecordsTotal());
        pageArray.setDraw(employeePage.getDraw());
        pageArray.setData(employeePage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private List<String> toStringList(Employee employee) {
        return Arrays.asList(employee.getId().toString(), sdf.format(employee.getCreate()), sdf.format(employee.getUpdate()),
                        employee.getFirstname(), employee.getLastname(), employee.getUsername());
    }

    public Page<Employee> getEmployees(PagingRequest pagingRequest, List<Employee> employees) {


        try {


            return getPage(employees, pagingRequest);

        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }

        return new Page<>();
    }

    private Page<Employee> getPage(List<Employee> employees, PagingRequest pagingRequest) {
        List<Employee> filtered = employees.stream()
                .sorted(sortEmployees(pagingRequest))
                .filter(filterEmployees(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = employees.stream()
                .filter(filterEmployees(pagingRequest))
                .count();

        Page<Employee> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<Employee> filterEmployees(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return employee -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return employee -> employee.getFirstname()
                .toLowerCase()
                .contains(value)
                || employee.getLastname()
                .toLowerCase()
                .contains(value)
                || employee.getUsername()
                .toLowerCase()
                .contains(value);
    }

    private Comparator<Employee> sortEmployees(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<Employee> comparator = EmployeeComparators.getComparator(column.getData(), order.getDirection());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }
}
