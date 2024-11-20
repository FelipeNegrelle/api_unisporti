package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user_plan", indexes = {
        @Index(name = "user_plan_start_date_idx", columnList = "start_date"),
        @Index(name = "user_plan_status_idx", columnList = "status")
})
public class UserPlan implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_plan", nullable = false)
    private Integer idUserPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", referencedColumnName = "id_plan", nullable = false)
    private Plan plan;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate = new Date();

//    @Column(name = "status", nullable = false, columnDefinition = "bpchar(1) DEFAULT 'P'")
    @Column(name = "status", nullable = false)
    private Character status = 'P'; // 'P' (Pending), 'C' (Canceled), 'D' (Done)
}
