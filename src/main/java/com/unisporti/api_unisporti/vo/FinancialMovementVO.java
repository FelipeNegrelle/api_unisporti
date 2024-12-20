package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class FinancialMovementVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_financial_movement")
    private Integer idFinancialMovement;

    @JsonProperty("id_user_plan")
    private Integer idUserPlan;

    @JsonProperty("value")
    private Float value;

    @JsonProperty("date_time_payment")
    private Date dateTimePayment = new Date();
}
