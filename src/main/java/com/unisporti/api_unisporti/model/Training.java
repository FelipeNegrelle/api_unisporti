package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "training", indexes = {
        @Index(name = "idx_training_week_day", columnList = "week_day"),
        @Index(name = "idx_training_id_modality", columnList = "id_modality"),
        @Index(name = "idx_training_id_place", columnList = "id_place")
})
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training", nullable = false)
    private Integer idTraining;

    @ManyToOne
    @JoinColumn(name = "id_modality", nullable = false)
    private Modality modality;

    @ManyToOne
    @JoinColumn(name = "id_place", nullable = false)
    private Place place;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "week_day", nullable = false)
    private Short weekDay;

    @Column(name = "start_hour", nullable = false)
    private Short startHour;

    @Column(name = "end_hour", nullable = false)
    private Short endHour;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
