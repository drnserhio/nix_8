package ua.com.alevel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("dateCreate")
    private Date dateCreate;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("dateUpdate")
    private Date dateUpdate;

}
