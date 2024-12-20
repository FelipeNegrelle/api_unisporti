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
@Table(name = "question")
public class Question implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question", nullable = false)
    private Integer idQuestion;

    @ManyToOne
    @JoinColumn(name = "id_poll", nullable = false)
    private Poll poll;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
