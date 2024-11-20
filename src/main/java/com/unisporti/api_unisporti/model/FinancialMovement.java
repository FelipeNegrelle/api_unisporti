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
@Table(name = "financial_movement")
public class FinancialMovement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financial_movement", nullable = false)
    private Integer idFinancialMovement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", referencedColumnName = "id_plan")
    private Plan plan;

    @Column(name = "value", nullable = false)
    private Float value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time_payment", nullable = false)
    private Date dateTimePayment = new Date();
}
