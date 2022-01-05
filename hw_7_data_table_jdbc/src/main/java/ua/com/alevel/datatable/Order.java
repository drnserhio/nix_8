package ua.com.alevel.datatable;

import lombok.*;
import ua.com.alevel.enumeration.Direction;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {

    private Integer column;
    private Direction direction;

}
