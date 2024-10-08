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
public class PlaceVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_place")
    private Integer idPlace;

    @JsonProperty("name")
    private String name;

    @JsonProperty("max_capacity")
    private Short maxCapacity;

    @JsonProperty("active")
    private Boolean active;
}
