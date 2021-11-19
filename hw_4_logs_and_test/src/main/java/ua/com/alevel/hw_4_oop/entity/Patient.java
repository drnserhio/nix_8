package ua.com.alevel.hw_4_oop.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private Long id;
    private String name;
    private String lastname;
    private int age;
    private String lastnameDoctor;
}
