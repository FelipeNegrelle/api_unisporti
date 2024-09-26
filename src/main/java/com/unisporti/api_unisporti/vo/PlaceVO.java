package com.unisporti.api_unisporti.vo;

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

    private Integer idPlace;
    private String name;
    private Short maxCapacity;
    private Boolean active;
}
