package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "modality")
public class Modality implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modality", nullable = false)
    private Integer idModality;

    @ManyToOne
    @JoinColumn(name = "id_instructor", nullable = false)
    private Instructor instructor;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    @Column(name = "max_participants")
    private Short maxParticipants;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
