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
public class TrainingUserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_training_user")
    private Integer idTrainingUser;

    @JsonProperty("id_training")
    private Integer idTraining;

    @JsonProperty("id_user")
    private Integer idUser;
}
