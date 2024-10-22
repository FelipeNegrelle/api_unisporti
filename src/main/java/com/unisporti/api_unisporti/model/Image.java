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
@Table(name = "images")
public class Image implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image", nullable = false)
    private Integer idImage;

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Column(name = "id_table", nullable = false)
    private Integer idTable;

    @Column(name = "ordering", nullable = false)
    private Short order = 0;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
