package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionTypeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_question_type")
    private Integer idQuestionType;

    @JsonProperty("question_type")
    private String questionType;

    @JsonProperty("active")
    private Boolean active = true;
}
