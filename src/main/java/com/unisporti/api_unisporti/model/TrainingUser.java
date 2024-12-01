package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "training_user")
public class TrainingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training_user", nullable = false)
    private Integer idTrainingUser;

    @ManyToOne
    @JoinColumn(name = "id_training", nullable = false)
    private Training training;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
