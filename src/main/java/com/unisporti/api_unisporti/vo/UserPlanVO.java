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
public class UserPlanVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_user_plan")
    private Integer idUserPlan;

    @JsonProperty("id_user")
    private Integer idUser;

    @JsonProperty("id_plan")
    private Integer idPlan;

    @JsonProperty("start_date")
    private Date startDate = new Date();

    @JsonProperty("status")
    private Character status = 'P'; // 'P' (Pending), 'C' (Canceled), 'D' (Done)
}
