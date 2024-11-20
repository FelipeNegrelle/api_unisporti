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
@Table(name = "instructor")
public class Instructor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor", nullable = false)
    private Integer idInstructor;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "degree_name", nullable = false)
    private String degreeName;

    @Column(name = "educational_institution", nullable = false)
    private String educationalInstitution;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
