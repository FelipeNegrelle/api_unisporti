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
@Table(name = "place")
public class Place implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlace;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "max_capacity")
    private Short maxCapacity;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
