package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class QuestionVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_question")
    private Integer idQuestion;

    @JsonProperty("id_poll")
    private Integer idPoll;

    @JsonProperty("id_question_type")
    private Integer idQuestionType;

    @JsonProperty("question")
    private String question;

    @JsonProperty("required")
    private Boolean required = false;

    @JsonProperty("active")
    private Boolean active = true;
}
