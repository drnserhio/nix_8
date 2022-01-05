package ua.com.alevel.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ua.com.alevel.enumeration.Direction;
import ua.com.alevel.model.impl.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EmployeeComparators {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<Employee>> map = new HashMap<>();

    static {
        map.put(new Key("id", Direction.asc), Comparator.comparing(Employee::getId));
        map.put(new Key("id", Direction.desc), Comparator.comparing(Employee::getId)
                .reversed());

        map.put(new Key("dateCreate", Direction.asc), Comparator.comparing(Employee::getCreate));
        map.put(new Key("dateCreate", Direction.desc), Comparator.comparing(Employee::getCreate)
                .reversed());

        map.put(new Key("dateupdate", Direction.asc), Comparator.comparing(Employee::getUpdate));
        map.put(new Key("dateupdate", Direction.desc), Comparator.comparing(Employee::getUpdate)
                .reversed());

        map.put(new Key("firstname", Direction.asc), Comparator.comparing(Employee::getFirstname));
        map.put(new Key("firstname", Direction.desc), Comparator.comparing(Employee::getFirstname)
                .reversed());

        map.put(new Key("lastname", Direction.asc), Comparator.comparing(Employee::getLastname));
        map.put(new Key("lastname", Direction.desc), Comparator.comparing(Employee::getLastname)
                .reversed());

        map.put(new Key("username", Direction.asc), Comparator.comparing(Employee::getUsername));
        map.put(new Key("username", Direction.desc), Comparator.comparing(Employee::getUsername)
                .reversed());

    }

    public static Comparator<Employee> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    private EmployeeComparators() {
    }
}
