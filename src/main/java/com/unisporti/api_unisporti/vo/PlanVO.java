package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PlanVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_plan", access = JsonProperty.Access.READ_ONLY)
    private Integer idPlan;

    @JsonProperty("id_modality")
    private Integer idModality;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price_cents")
    private Short priceCents;

    @JsonProperty("duration_days")
    private Short durationDays;

    @JsonProperty("active")
    private Boolean active = true;
}
