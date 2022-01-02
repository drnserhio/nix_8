package ua.com.alevel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUser {

    private Long id;
    private Date create;
    private Date update;

}
