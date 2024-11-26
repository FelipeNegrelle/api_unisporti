package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class InstructorAthleteVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_user")
    private Integer idUser;

    @JsonProperty(value = "id_modality")
    private Integer idModality;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("modality_description")
    private String modalityDescription;
}
