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
public class InstructorVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id_instructor", access = JsonProperty.Access.READ_ONLY)
    private Integer idInstructor;

    @JsonProperty("id_user")
    private Integer idUser;

    @JsonProperty("degree_name")
    private String degreeName;

    @JsonProperty("educational_institution")
    private String educationalInstitution;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("active")
    private Boolean active;
}
