package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "question_type")
public class QuestionType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question_type", nullable = false)
    private Integer idQuestionType;

    @Column(name = "type_name", length = 50, nullable = false)
    private String typeName;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}

