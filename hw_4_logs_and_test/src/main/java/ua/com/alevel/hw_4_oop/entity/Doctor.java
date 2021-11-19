package ua.com.alevel.hw_4_oop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    private Long id;
    private String name;
    private String lastname;
    private int age;
    private String email;
}
