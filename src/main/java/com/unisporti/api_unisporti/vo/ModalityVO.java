package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
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
public class ModalityVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_modality", access = JsonProperty.Access.READ_ONLY)
    private Integer idModality;

    @JsonProperty("id_instructor")
    private Integer idInstructor;

    @JsonProperty(value = "description")
    private String description = "";

    @JsonProperty("max_participants")
    private Short maxParticipants;

    @JsonProperty(value = "active")
    private Boolean active;
}
