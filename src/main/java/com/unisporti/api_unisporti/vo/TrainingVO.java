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
public class TrainingVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_training")
    private Integer idTraining;

    @JsonProperty("id_modality")
    private Integer idModality;

    @JsonProperty("id_place")
    private Integer idPlace;

    @JsonProperty("description")
    private String description;

    @JsonProperty("week_day")
    private Short weekDay;

    @JsonProperty("start_hour")
    private Short startHour;

    @JsonProperty("end_hour")
    private Short endHour;

    @JsonProperty("active")
    private Boolean active = true;
}
