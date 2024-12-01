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
public class MyPaymentsVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_financial_movement")
    private Integer idFinancialMovement;

    @JsonProperty(value = "value")
    private Float value;

    @JsonProperty(value = "plan_name")
    private String planName;

    @JsonProperty(value = "modality_name")
    private String modalityName;

    @JsonProperty(value = "date_time_payment")
    private String dateTimePayment;
}
