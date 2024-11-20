package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "plan", indexes = {
        @Index(name = "idx_plan_duration_days", columnList = "duration_days"),
        @Index(name = "idx_plan_name", columnList = "name"),
        @Index(name = "idx_plan_price_cents", columnList = "price_cents")
})
public class Plan implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan", nullable = false)
    private Integer idPlan;

    @ManyToOne
    @JoinColumn(name = "id_modality", nullable = false)
    private Modality modality;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "price_cents", nullable = false)
    private Short priceCents;

    @Column(name = "duration_days", nullable = false)
    private Short durationDays;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
